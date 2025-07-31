# Java Swing Snake Game 🎮

A classic Snake game built using Java Swing, converted to a browser-playable game using CheerpJ.

## 🐍 Features
- Classic snake movement and apple collection
- Smooth rendering using `paintComponent`
- Custom icons (head, dot, apple)
- Timer-based game loop

## 📦 How to Compile

```bash
javac --release 17 SnakeGame.java
jar cfe SnakeGame.jar SnakeGame *.class icons/*

npm install -g http-server
http-server -p 8080
