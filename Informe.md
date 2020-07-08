Informe TPE POO:
*Agregado de funcionalidades:
 -Se agregaron tres nuevas figuras (Cuadrado, Elipse y Línea). Las mismas extienden a la clase Figure ya sea directamente o por medio de herencia de clases intermedias.
 -Se agrego la clase SelectionBox. La misma se hizo para el manejo y aplicación de distintas funcionalidades de las figuras seleccionadas. Se pueden seleccionar individualmente o
 múltiple por medio de la creación de un rectángulo imaginario que las encierra. Los métodos de esta clase se acceden por medio de la clase CanvasState que relaciona el frontend con el backends.
 -Se agrego a la barra lateral los botones que permiten alterar los atributos de la figura(grosor de borde y colores tanto de relleno como de linea) por medio de las clases Slider 
 y ColorPicker respectivamente.
 -Se agregaron las funcionalidades "Al frente" y "Al fondo". Estas funcionalidades permiten traer al frente o llevar al fondo las figuras seleccionadas.
 -Se agrego la funcionalidad de eliminación de figuras. Al pulsar el botón "Borrar" esto eliminará todas las figuras que estén seleccionadas en el momento.
*Modificación de código:
 Por otro lado, también se modificó el código provisto:
 -Se removió el método figureBelongs que se hallaba en la clase PaintPane del frontend y se reemplazó por dos métodos abstractos con el mismo nombre en la clase Figure ya que es
 entendible que cada figura deba saber identificarse a si misma.
 -Se modifico el método move de la clase PaintPane. De la misma forma que con el método figureBelongs se agregó a la clase abstract Figure el método abstracto Move y cada figura
 sobreescribe dicho método dependiendo de cómo esta deba moverse.
 -Se modifico la validación de puntos al crear una figura para que esta validación no afecte a la clase "Line" o "Circle" ya que estas clases no deberían tener restringidos los
 puntos de creación.
 -Se implemento el método Equals y HashCode en la clase Point. Esta implementación es necesaria para poder utilizar correctamente los métodos para selección particular y selección
 múltiple.
 -Se agregaron las variables de instancia "linecolor","linewidth","fillercolor" y se añadieron getters y setters a la clase Figure para que cada figura almacene sus respectivos
 atributos. Ademas se modifico el metodo redrawCanvas para que al mostrar las figuras en pantalla este metodo busque los atributos en las figuras con los getters.
 -Se modifico la lista de la clase canvasState que almacenaba todas las figuras creadas y se la reemplazo por una LinkedList. Esto es para asi poder utilizar los metodos addFirst
 y addLast que se utilizaran para las funcionalidades de "traer al frent" y "llevar al fondo".

*Problemas encontrados durante el desarrollo:
 -Hubo problemas cuando se declaró el repositorio local en github al inicio del proyecto. Esto provoco un problema con la declaración de los paquetes y los imports que como
 consecuencia causaba que cualquiera que descargara el proyecto desde el repositorio remoto no pudiera ejecutar el proyecto por errores de compilación.
 -Mucho del tiempo del proyecto fue dedicado a averiguar el funcionamiento del frontend y lo metodos de javafx para poder relacionar los objetos del frontend con los del backend.
 Por ejemplo: averiguar cómo funciona el slider para que el movimiento del slider efectivamente cambie el valor del grosor de línea de la figura seleccionada.

