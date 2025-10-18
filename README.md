# Sistema de Facturación para Local de Bebidas

Un sistema simple y fácil de usar para gestionar inventario y facturación de un local de bebidas.

## 🚀 Características

- **Gestión de Inventario**: Control de stock de bebidas por categorías
- **Facturación Automática**: Creación de facturas con cálculo automático de impuestos
- **Reportes Visuales**: Análisis de ventas y productos más vendidos
- **Interfaz Web Simple**: Fácil de usar desde cualquier navegador
- **Base de Datos Embebida**: No requiere instalación de base de datos
- **JAR Ejecutable**: Solo ejecutar el archivo JAR para usar el sistema

## 📋 Requisitos

- Java 19 o superior (recomendado Java 21)
- Navegador web moderno

## 🛠️ Instalación y Uso

### Opción 1: Ejecutar JAR (Recomendado)

#### En macOS/Linux:
1. Descarga el archivo `facturacion-0.0.1-SNAPSHOT.jar`
2. Descarga el script `iniciar-sistema.sh`
3. Abre una terminal en la carpeta donde están los archivos
4. Ejecuta:
   ```bash
   chmod +x iniciar-sistema.sh
   ./iniciar-sistema.sh
   ```

#### En Windows:
1. Descarga el archivo `facturacion-0.0.1-SNAPSHOT.jar`
2. Descarga el script `iniciar-sistema.bat`
3. Haz doble clic en `iniciar-sistema.bat`

#### Ejecución manual:
```bash
java -jar facturacion-0.0.1-SNAPSHOT.jar
```

4. Abre tu navegador y ve a: `http://localhost:8080`

### Opción 2: Compilar desde Código Fuente

1. Asegúrate de tener Java 21 y Maven instalados
2. Clona o descarga este proyecto
3. En la carpeta del proyecto, ejecuta:
   ```bash
   mvn clean package
   ```
4. Ejecuta el JAR generado:
   ```bash
   java -jar target/facturacion-0.0.1-SNAPSHOT.jar
   ```

## 🎯 Cómo Usar el Sistema

### 1. Gestión de Productos
- Ve a la sección "Productos" para agregar, editar o eliminar bebidas
- Las bebidas se organizan por categorías: Cerveza, Vino, Whisky, Vodka, Ron, Tequila, Champagne, Licores, etc.
- Puedes actualizar el stock de productos existentes
- El sistema alerta cuando el stock está bajo (menos de 10 unidades)

### 2. Facturación
- Ve a la sección "Facturación" para crear nuevas facturas
- Selecciona productos de la lista y especifica cantidades
- El sistema calcula automáticamente subtotal, impuestos (19%) y total
- Al procesar la factura, se actualiza automáticamente el stock

### 3. Reportes
- Ve a la sección "Reportes" para ver análisis de ventas
- Filtra por fechas para ver ventas en períodos específicos
- Visualiza productos más vendidos y ventas por categoría
- Monitorea productos con stock bajo

## 📊 Datos de Prueba

El sistema viene precargado con datos de ejemplo de bebidas populares:
- Cervezas: Corona, Heineken, Stella Artois, Budweiser, Quilmes
- Vinos: Malbec, Cabernet Sauvignon, Chardonnay, Merlot
- Whisky: Johnnie Walker, Chivas Regal, Jack Daniel's, Jameson
- Vodka: Absolut, Smirnoff, Grey Goose
- Ron: Bacardí, Havana Club, Captain Morgan
- Tequila: Jose Cuervo, Don Julio, Patrón
- Champagne: Moët & Chandon, Veuve Clicquot, Dom Pérignon
- Licores: Baileys, Kahlúa, Cointreau

## 🔧 Configuración

### Puerto del Servidor
Por defecto el sistema corre en el puerto 8080. Para cambiarlo, modifica el archivo `application.properties`:
```properties
server.port=8080
```

### Base de Datos
El sistema usa H2 Database embebida. Los datos se almacenan en memoria y se reinician cada vez que se ejecuta la aplicación.

Para acceder a la consola de H2 (solo para desarrollo):
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:facturacion`
- Usuario: `sa`
- Contraseña: (dejar vacío)

## 📱 Interfaz de Usuario

El sistema tiene una interfaz web moderna y responsive que funciona en:
- Computadoras de escritorio
- Tablets
- Teléfonos móviles

### Navegación
- **Inicio**: Dashboard con estadísticas generales
- **Productos**: Gestión de inventario
- **Facturación**: Creación de facturas
- **Reportes**: Análisis y estadísticas

## 🚨 Solución de Problemas

### El JAR no ejecuta
- Verifica que tengas Java 21 o superior instalado
- Ejecuta `java -version` para verificar la versión
- Asegúrate de que el archivo JAR no esté corrupto

### No se puede acceder desde el navegador
- Verifica que el puerto 8080 no esté siendo usado por otra aplicación
- Cambia el puerto en `application.properties` si es necesario
- Asegúrate de usar la URL correcta: `http://localhost:8080`

### Los datos se pierden al reiniciar
- Esto es normal, el sistema usa base de datos en memoria
- Los datos se reinician cada vez que se ejecuta la aplicación
- Para persistir datos, se necesitaría configurar una base de datos externa

## 📞 Soporte

Este sistema está diseñado para ser simple y fácil de usar. Si tienes problemas:

1. Verifica que Java 21 esté instalado correctamente
2. Asegúrate de que el puerto 8080 esté disponible
3. Revisa la consola donde ejecutaste el JAR para ver mensajes de error

## 🔄 Actualizaciones

Para actualizar el sistema:
1. Descarga la nueva versión del JAR
2. Detén la aplicación actual (Ctrl+C en la consola)
3. Ejecuta el nuevo JAR
4. Los datos se reiniciarán con la nueva versión

---

**¡Disfruta usando tu sistema de facturación!** 🍺🍷🥃
