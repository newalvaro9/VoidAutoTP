# Void Auto Teleport Plugin Documentation

## Overview

The Void Auto Teleport Plugin is designed to automatically teleport players who fall into the void to a specified location within a selected Minecraft world. This documentation will guide you through the setup and usage of the plugin.

## Installation
Download the Void Auto Teleport Plugin JAR file.
Place the JAR file into the "**plugins**" folder of your Minecraft server.
Restart your Minecraft server.
Configure the plugin at _./plugins/VoidAutoTP/config.yml_

## Configuration

The configuration file (config.yml) allows you to customize the behavior of the plugin. Here's how you can configure it:

- **enabled**: Set to true to enable the plugin, or false to disable it.
- **void_layer**: The Y-coordinate of the void layer where players will be teleported if they fall.
- **message**: The message displayed to players when they are teleported. Leave empty ("") for no message.
- **teleport**:
    - world_name: The name of the world where players will be teleported.
    - **x**: The X-coordinate of the teleportation destination.
    - **y**: The Y-coordinate of the teleportation destination.
    - **z**: The Z-coordinate of the teleportation destination.
    - **yaw**: The yaw angle (facing direction) of the player upon teleportation.
    - **pitch**: The pitch angle (look up/down) of the player upon teleportation.
## Usage
Once the plugin is installed and configured, it will automatically teleport players who fall into the void to the specified location within your Minecraft world. Players will receive the configured message upon teleportation.

## Example Configuration
```yml
enabled: true
void_layer: -59
message: "You have been saved from the void!"
teleport:
  world_name: world
  x: 0.5
  y: 69
  z: 0.5
  yaw: 180.0
  pitch: 0.0
```
## Support
If you encounter any issues or have questions about the Void Auto Teleport Plugin, please refer to the plugin's documentation or reach newalvaro9 through the support discord server.
