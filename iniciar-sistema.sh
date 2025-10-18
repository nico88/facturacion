#!/bin/bash

# Script para iniciar el Sistema de Facturación para Local de Bebidas
# Autor: Sistema de Facturación
# Fecha: $(date)

echo "=========================================="
echo "  Sistema de Facturación - Local de Bebidas"
echo "=========================================="
echo ""
echo "Iniciando el sistema..."
echo ""

# Verificar si Java está instalado
if ! command -v java &> /dev/null; then
    echo "❌ Error: Java no está instalado o no está en el PATH"
    echo "   Por favor instala Java 19 o superior"
    echo "   Descarga desde: https://adoptium.net/"
    exit 1
fi

# Verificar versión de Java
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 19 ]; then
    echo "❌ Error: Se requiere Java 19 o superior"
    echo "   Versión actual: $(java -version 2>&1 | head -n 1)"
    echo "   Descarga desde: https://adoptium.net/"
    exit 1
fi

echo "✅ Java detectado: $(java -version 2>&1 | head -n 1)"
echo ""

# Verificar si el JAR existe
if [ ! -f "target/facturacion-0.0.1-SNAPSHOT.jar" ]; then
    echo "❌ Error: No se encontró el archivo JAR"
    echo "   Ejecuta primero: mvn clean package"
    exit 1
fi

echo "✅ Archivo JAR encontrado"
echo ""

# Iniciar la aplicación
echo "🚀 Iniciando la aplicación..."
echo "   URL: http://localhost:8080"
echo "   Presiona Ctrl+C para detener"
echo ""

java -jar target/facturacion-0.0.1-SNAPSHOT.jar
