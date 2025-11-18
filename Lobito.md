> **Escuela Politécnica Nacional**  
> **Metodologías Ágiles**  
> **Integrantes:**  
> **Samira Arízaga**  
> **Paúl Dávila**
> **Salma Morales**  
> **Danna Morales**  
> **Sebastián Sarasti**

# **Ejercicio Lobito**
## Requerimientos 

**RF01.** El sistema debe permitir que el usuario seleccione libremente qué elemento desea subir a la barca antes de cada movimiento, el observador siempre debe estar en la barca para manejarla, pudiendo elegir entre el Lobo, Caperucita o las Uvas.

**RF02.** El sistema debe aplicar la restricción de capacidad de la barca, permitiendo que esta transporte únicamente al Observador acompañado de un solo elemento adicional, ya sea el Lobo, Caperucita o las Uvas, garantizando que nunca se exceda el límite máximo permitido de dos elementos simultáneos.

**RF03.** El sistema debe ejecutar el cruce del río moviendo la barca de una orilla a la otra únicamente cuando la combinación de elementos a bordo cumpla con las reglas establecidas, considerando la presencia obligatoria del Observador y la cantidad máxima de carga permitida, y actualizando la posición de todos los elementos después del movimiento.

**RF04.** El sistema debe verificar constantemente que no ocurran situaciones prohibidas en ninguna de las orillas, específicamente, debe impedir que el Lobo quede solo con Caperucita sin supervisión del Observador y también debe evitar que Caperucita quede sola con las Uvas, bloqueando cualquier jugada que genere estos escenarios y mostrando el mensaje de fin de juego correspondiente.

**RF05.** El sistema debe mostrar en todo momento el estado actual del juego, indicando claramente en qué orilla se encuentran el Observador, el Lobo, Caperucita y las Uvas, cuál de ellos está dentro de la barca y en qué posición se encuentra esta, ya sea en la orilla izquierda o en la orilla derecha, actualizando la información luego de cada acción del usuario.

**RF06.** El sistema debe ofrecer la opción de reiniciar la partida en cualquier momento, restableciendo la barca y todos los elementos a su ubicación inicial en la orilla de partida, permitiendo comenzar nuevamente el desafío.

**RF07.** El sistema debe detectar cuando el Observador, el Lobo, Caperucita y las Uvas hayan sido trasladados con éxito a la orilla final cumpliendo todas las reglas del juego, momento en el cual debe notificar al usuario que ha completado satisfactoriamente el problema.

## Diagrama de Casos de Uso
<img width="666" height="353" alt="image" src="https://github.com/user-attachments/assets/3bb00283-0bab-4f8e-add0-167d2530d956" />

## Diagrama de Clases
<img width="455" height="656" alt="image" src="https://github.com/user-attachments/assets/4c805942-2ffd-4663-b05e-156838daf8e6" />

## Código
https://github.com/BaphomeT-T/Lobito 
