# Quilt Parsers
A collection of utilities involving data files.

## Modules
### JSON
A simple JSON, JSONC, and JSON5 reader and writer, modeled after GSON's JsonReader and JsonWriter interface.
Requires Java 8.
```kotlin
dependencies {
    implementation("org.quiltmc.parsers:json:0.1.0")
}
```
### GSON
An adapter of the JSON module to GSON, allowing parsers JsonReader/Writer objects to be used with methods in the `Gson` class.
Requires Java 11.
```kotlin
dependencies {
    implementation("org.quiltmc.parsers:gson:0.1.0") // pulls in correct versions of parsers-json and gson
}
```

## FAQ
### Can you add $FORMAT?
Probably! Feel free to open an issue or submit a PR!