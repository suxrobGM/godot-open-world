plugins {
    id("com.utopia-rise.godot-kotlin-jvm") version "0.14.3-4.5.1"
    id("com.android.library") version "8.13.0" apply false
    kotlin("android") version "2.1.0" apply false
    kotlin("plugin.compose") version "2.1.0" apply false
}

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

godot {
    // ---------Setup-----------------

    // the script registration which you'll attach to nodes are generated into this directory
    registrationFileBaseDir.set(projectDir.resolve("gdj"))

    // Create .gdj files from all JVM scripts
    isRegistrationFileGenerationEnabled.set(true)

    // Use fully qualified names for script registration files (e.g. "com.example.MyNode.gdj" instead of "MyNode.gdj")c
    isFqNameRegistrationEnabled.set(true)

    // defines whether the script registration files should be generated hierarchically according to the classes package path or flattened into `registrationFileBaseDir`
    //isRegistrationFileHierarchyEnabled.set(true)

    // defines whether your scripts should be registered with their fqName or their simple name (can help with resolving script name conflicts)
    //isFqNameRegistrationEnabled.set(false)

    // ---------Android----------------

    // NOTE: Make sure you read: https://godot-kotl.in/en/stable/user-guide/exporting/#android as not all jvm libraries are compatible with android!
    // IMPORTANT: Android export should to be considered from the start of development!
    isAndroidExportEnabled.set(true)
    // Set these paths based on your Android SDK installation:
    // d8ToolPath.set(File("C:/Users/admin/AppData/Local/Android/Sdk/build-tools/34.0.0/d8.bat"))
    // androidCompileSdkDir.set(File("C:/Users/admin/AppData/Local/Android/Sdk/platforms/android-34"))

    // --------IOS and Graal------------

    // NOTE: this is an advanced feature! Read: https://godot-kotl.in/en/stable/user-guide/advanced/graal-vm-native-image/
    // IMPORTANT: Graal Native Image needs to be considered from the start of development!
    //isGraalNativeImageExportEnabled.set(IS_GRAAL_VM_ENABLED)
    //graalVmDirectory.set(File("GRAAL_VM_DIR"))
    //windowsDeveloperVCVarsPath.set(File("WINDOWS_DEVELOPER_VS_VARS_PATH"))
    //isIOSExportEnabled.set(IS_IOS_ENABLED)

    // --------Library authors------------

    // library setup. See: https://godot-kotl.in/en/stable/develop-libraries/
    //classPrefix.set("MyCustomClassPrefix")
    //projectName.set("LibraryProjectName")
    //projectName.set("LibraryProjectName")
}

// Jetpack Compose dependencies for Android
dependencies {
    // Compose BOM for version management
    implementation(platform("androidx.compose:compose-bom:2025.10.01"))

    // Compose UI dependencies
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:1.4.0")

    // Activity Compose for integration
    implementation("androidx.activity:activity-compose:1.11.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
}
