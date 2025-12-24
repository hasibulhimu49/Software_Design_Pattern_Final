@echo off
echo ========================================
echo    SNAKE GAME - COMPLETE BUILD
echo ========================================
echo.

echo Step 1: Cleaning previous build...
if exist bin rmdir /s /q bin
mkdir bin
echo.

echo Step 2: Compiling utility classes...
javac -d bin src/utils/GameConfig.java
if %errorlevel% neq 0 goto error
echo.

echo Step 3: Compiling snake package...
javac -d bin -cp bin src/snake/Direction.java
javac -d bin -cp bin src/snake/SnakeBodyPart.java
javac -d bin -cp bin src/snake/Snake.java
if %errorlevel% neq 0 goto error
echo.

echo Step 4: Compiling strategy package...
javac -d bin -cp bin src/strategy/MovementStrategy.java
javac -d bin -cp bin src/strategy/NormalMovement.java
javac -d bin -cp bin src/strategy/FastMovement.java
javac -d bin -cp bin src/strategy/ReverseMovement.java
if %errorlevel% neq 0 goto error
echo.

echo Step 5: Compiling food package...
javac -d bin -cp bin src/food/FoodType.java
javac -d bin -cp bin src/food/Food.java
javac -d bin -cp bin src/food/NormalFood.java
javac -d bin -cp bin src/food/GoldenFood.java
javac -d bin -cp bin src/food/SpeedFood.java
javac -d bin -cp bin src/food/ReverseFood.java
javac -d bin -cp bin src/food/FoodFactory.java
if %errorlevel% neq 0 goto error
echo.

echo Step 6: Compiling observer package...
javac -d bin -cp bin src/observer/GameEvent.java
javac -d bin -cp bin src/observer/GameObserver.java
javac -d bin -cp bin src/observer/ScoreObserver.java
if %errorlevel% neq 0 goto error
echo.

echo Step 7: Compiling game package...
javac -d bin -cp bin src/game/GameModel.java
javac -d bin -cp bin src/game/GameView.java
javac -d bin -cp bin src/game/GameController.java
if %errorlevel% neq 0 goto error
echo.

echo Step 8: Compiling Main class...
javac -d bin -cp bin src/Main.java
if %errorlevel% neq 0 goto error
echo.

echo ========================================
echo    BUILD SUCCESSFUL!
echo ========================================
echo.
echo Starting Snake Game...
echo.
echo CONTROLS:
echo - Arrow Keys: Move Snake
echo - SPACE: Pause/Resume
echo - R: Restart Game
echo - F11: Toggle Fullscreen
echo.
echo ========================================
java -cp bin Main
goto end

:error
echo.
echo ========================================
echo    BUILD FAILED!
echo ========================================
echo Please check the error messages above.
pause
exit /b 1

:end
pause