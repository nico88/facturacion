@echo off
REM Script para iniciar el Sistema de Facturación para Local de Bebidas
REM Autor: Sistema de Facturación
REM Fecha: %date%

echo ==========================================
echo   Sistema de Facturación - Local de Bebidas
echo ==========================================
echo.
echo Iniciando el sistema...
echo.

REM Verificar si Java está instalado
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Error: Java no está instalado o no está en el PATH
    echo    Por favor instala Java 19 o superior
    echo    Descarga desde: https://adoptium.net/
    pause
    exit /b 1
)

echo ✅ Java detectado
echo.

REM Verificar si el JAR existe
if not exist "target\facturacion-0.0.1-SNAPSHOT.jar" (
    echo ❌ Error: No se encontró el archivo JAR
    echo    Ejecuta primero: mvn clean package
    pause
    exit /b 1
)

echo ✅ Archivo JAR encontrado
echo.

REM Iniciar la aplicación
echo 🚀 Iniciando la aplicación...
echo    URL: http://localhost:8080
echo    Presiona Ctrl+C para detener
echo.

java -jar target\facturacion-0.0.1-SNAPSHOT.jar
