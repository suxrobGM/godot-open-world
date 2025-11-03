package godot

import godot.annotation.Export
import godot.annotation.RegisterClass
import godot.annotation.RegisterFunction
import godot.annotation.RegisterProperty
import godot.api.CharacterBody3D
import godot.api.Input
import godot.api.AnimationPlayer
import godot.api.Node3D
import godot.core.Vector2
import godot.core.Vector3
import godot.global.GD

@RegisterClass
class PlayerController : CharacterBody3D() {

	@Export
	@RegisterProperty
	var speed = 5.0

	@Export
	@RegisterProperty
	var jumpVelocity = 4.5

	@Export
	@RegisterProperty
	var mouseSensitivity = 0.003

	private var gravity = 9.8
	private var animationPlayer: AnimationPlayer? = null
	private var model: Node3D? = null

	@RegisterFunction
	override fun _ready() {
		GD.print("Player controller initialized!")

		// Get the model and its AnimationPlayer
		model = getNode("Model") as? Node3D

		// Try to get AnimationPlayer from the model (GLTF files have built-in AnimationPlayer)
		if (model != null) {
			animationPlayer = model!!.getNodeOrNull("AnimationPlayer") as? AnimationPlayer
			if (animationPlayer == null) {
				// Try other common paths
				val modelChild = model!!.getChild(0)
				animationPlayer = modelChild?.getNodeOrNull("AnimationPlayer") as? AnimationPlayer
			}
		}

		if (animationPlayer != null) {
			GD.print("AnimationPlayer found!")
		} else {
			GD.print("Warning: AnimationPlayer not found in model")
		}
	}

	@RegisterFunction
	override fun _physicsProcess(delta: Double) {
		// Apply gravity
		val currentVelocity = velocity
		if (!isOnFloor()) {
			velocity = Vector3(
				currentVelocity.x,
				currentVelocity.y - gravity * delta,
				currentVelocity.z
			)
		}

		// Handle jump
		val input = Input
		if (input.isActionJustPressed("ui_accept") && isOnFloor()) {
			velocity = Vector3(
				currentVelocity.x,
				jumpVelocity,
				currentVelocity.z
			)
		}

		// Get input direction
		val inputDir = Vector2(
			input.getActionStrength("ui_right") - input.getActionStrength("ui_left"),
			input.getActionStrength("ui_down") - input.getActionStrength("ui_up")
		)

		// Calculate movement direction
		val direction = Vector3(inputDir.x, 0.0, inputDir.y).normalized()

		// Rotate model to face movement direction
		if (direction.length() > 0 && model != null) {
			val targetRotation = kotlin.math.atan2(direction.x, direction.z)
			val currentRotation = model!!.rotation
			model!!.rotation = Vector3(
				currentRotation.x,
				targetRotation,
				currentRotation.z
			)
		}

		// Apply movement
		val vel = velocity
		val isMoving = direction.length() > 0

		velocity = if (isMoving) {
			Vector3(
				direction.x * speed,
				vel.y,
				direction.z * speed
			)
		} else {
			Vector3(
				vel.x * 0.9, // Damping
				vel.y,
				vel.z * 0.9
			)
		}

		moveAndSlide()

		// Update animations
		updateAnimation(isMoving, isOnFloor())
	}

	private fun updateAnimation(isMoving: Boolean, isOnFloor: Boolean) {
		if (animationPlayer == null) return

		// Priority: jumping > walking > idle
		when {
			!isOnFloor -> {
				// Play jump/falling animation if available
				if (animationPlayer!!.hasAnimation("jump")) {
					playAnimation("jump")
				} else if (animationPlayer!!.hasAnimation("Jump")) {
					playAnimation("Jump")
				}
			}
			isMoving -> {
				// Play walk/run animation
				if (animationPlayer!!.hasAnimation("walk")) {
					playAnimation("walk")
				} else if (animationPlayer!!.hasAnimation("Walk")) {
					playAnimation("Walk")
				} else if (animationPlayer!!.hasAnimation("run")) {
					playAnimation("run")
				} else if (animationPlayer!!.hasAnimation("Run")) {
					playAnimation("Run")
				}
			}
			else -> {
				// Play idle animation
				if (animationPlayer!!.hasAnimation("idle")) {
					playAnimation("idle")
				} else if (animationPlayer!!.hasAnimation("Idle")) {
					playAnimation("Idle")
				}
			}
		}
	}

	private fun playAnimation(animName: String) {
		if (animationPlayer?.currentAnimation != animName) {
			animationPlayer?.play(animName)
		}
	}
}
