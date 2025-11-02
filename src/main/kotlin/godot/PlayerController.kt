package godot

import godot.annotation.Export
import godot.annotation.RegisterClass
import godot.annotation.RegisterFunction
import godot.annotation.RegisterProperty
import godot.api.CharacterBody3D
import godot.api.Input
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

	@RegisterFunction
	override fun _ready() {
		GD.print("Player controller initialized!")
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

		// Apply movement
		val vel = velocity

		velocity = if (direction.length() > 0) {
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
	}
}
