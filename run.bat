@echo off
echo -------------------------------
echo Compiling Java files...
echo -------------------------------

set SRC=src\main\java
set OUT=out

:: Create output folder if not exists
if not exist %OUT% mkdir %OUT%

:: Compile all Java files recursively
for /R %SRC% %%f in (*.java) do (
    javac -d %OUT% "%%f"
)

echo -------------------------------
echo Running AuthController...
echo -------------------------------

java -cp %OUT% com.portfolio.controller.AuthController

pause
