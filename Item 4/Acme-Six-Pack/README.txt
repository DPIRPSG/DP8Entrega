A continuación se detallan lo que hemos considerado aspectos importantes a tener en cuenta en nuestra implementación del proyecto y que no están reflejados en otras zonas del mismo:

Respecto a las "queries":
-Query C/1: Aunque podría tener más sentido no tener en cuenta los "orders" cancelados, hemos decidido tener en cuenta todo tipo de "orders" que hayan sido creadas debido a que el enunciado pide exactamente los "orders placed", es decir, pedidos realizados, sin distinción alguna en cuanto a si han sido cancelados, entregados o cualquier estado del mismo.

-Query B/5: Esta query referente al ratio la hemos interpretado como el número de pedidos cancelados en el mes actual entre el número de pedidos encargados dicho mes ("Placed" en el vocabulario del Statement). Según otras interpretaciones, podría entenderse entre el número de pedidos cancelados en el mes actual entre el número de pedidos encargados sin tener en cuenta la fecha de encargo. Esto nos pareció poco útil ya que en una cadena de gran tamaño a medida que pasase el tiempo el ratio sería muy próximo a cero, sirviendo el ratio de poco como método estadístico.

-Query A/1: Se nos pide el item (o items) que tiene(o tengan) comentarios sin más. Sin embargo hemos visto oportuno filtrarlos no teniendo en cuenta aquellos items que hayan sido borrados (lo cuál sólo implica un cambio de valor en el atributo "deleted" de cada item).

Respecto al D06-Controllers
- Nos hemos percatado que nos piden lo siguiente: "It’s very important that your “PopulateDatabase.xml” file provides at least six items and six contracts.". En el dominio de nuestro problema no hemos encontrado una equivalencia a los contracts con lo que hemos decidido interpretarlos como los pedidos (Orders).