A continuaci�n se detallan lo que hemos considerado aspectos importantes a tener en cuenta en nuestra implementaci�n del proyecto y que no est�n reflejados en otras zonas del mismo:

Respecto a las "queries":
- Para la query que realiza la acci�n "The most popular gym/s", hemos interpretado que se toma el gym con mayor n�mero de feePayment activos a fecha de hoy. Se podr�a interpretar de otras formas pero vemos m�s l�gico que un gym se considere popular respecto al n�mero de usuarios activos y no respecto a su historial.

- Para la query que realiza la acci�n "The least popular gyms/s.", se toma el gym con el menor n�mero de feePayment activos a fecha de hoy. Si el gym no tiene actualmente ning�n feePayment activo, no se contar� como el menos popular. Al igual que la query anterior, hay otras formas de interpretar lo que nos demandan pero vemos mejor no contabilizar los gimnasios sin feePayment activos ya que creemos que no aporta verdadera informaci�n. Creemos esto porque esta situaci�n s�lo ocurre cuando un gimnasio acaba de iniciarse o cuando est� al borde de la bancarrota y por tanto ser� eliminado de los gimnasios disponibles.

- Para la query que realiza la acci�n "The average number of messages in an actor�s message boxes", ante la ambieg�edad del texto, lo hemos interpretado como la media de todos los mensajes enviados por todos los actor. Hemos decidido hacerlo as� ya que es la forma m�s intuitiva de ver la actividad de los actores en el sistema de mensajes. 


Respecto al despliegue:
- Al desplegar el proyecto puede aparecer el error "java.lang.OutOfMemoryError: PermGen space" lo que provoca:
  + Que el servidor se estanque y no se pueda cargar el .war.
  + Que se muestre un mensaje al montar un .war tras hacer Undeploy de otro: FAIL - Deployed application at context path / but context failed to start.
  + Que se muestre un mensaje al montar un .war tras hacer Undeploy de otro: FAIL - Encountered exception javax.management.RuntimeErrorException: Error invoking method check.
- Se puede poner en otra ruta mientras otra instancia est� corriendo sin hacer Undeploy.
- Para solucionar este error se pueden tomar dos opciones:
  + Opci�n 1: Vaciar cache
    - Hacer Undeploy de todas las aplicaciones de Tomcat (excepto "/manager"). En caso de que no se pueda se reinicia la maquina virtual y se hace Undeploy.
    - Despu�s de hacer Undeploy en todas, es necesario reiniciar la m�quina virtual.
    - Una vez reiniciada se puede volver a montar el .war.
  + Opci�n 2: Aumentar cach�
    - Entrar en la configuraci�n de Tomcat desde la bandeja de notificaciones
    - Ir a la pesta�a "Java"
    - Al final de Java Options a�adir las siguientes l�neas:

        -Xms128m
        -Xmx1024m
        -XX:PermSize=64m
        -XX:MaxPermSize=256m

     PermSize refleja la capacidad del PermGen inicial, MaxPermSize refleja la cantidad m�xima de PermGen, es decir, cuanto mayor sea, m�s proyectos vamos a poder cargar y descargar sin necesidad de reiniciar la m�quina virtual para vaciar la cache.
