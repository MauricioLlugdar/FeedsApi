---
title: Laboratorio de Programación Orientada a Objetos
author: Nicolas Cagliero, Mauricio Llugdar, Antonella Loyola
---

El enunciado del laboratorio se encuentra en [este link](https://docs.google.com/document/d/1wLhuEOjhdLwgZ4rlW0AftgKD4QIPPx37Dzs--P1gIU4/edit#heading=h.xe9t6iq9fo58).

# 1. Tareas
Pueden usar esta checklist para indicar el avance.

## Verificación de que pueden hacer las cosas.
- [x] Java 17 instalado. Deben poder compilar con `make` y correr con `make run` para obtener el mensaje de ayuda del programa.

## 1.1. Interfaz de usuario
- [x] Estructurar opciones
- [x] Construir el objeto de clase `Config`

## 1.2. FeedParser
- [x] `class Article`
    - [x] Atributos
    - [x] Constructor
    - [x] Método `print`
    - [x] _Accessors_
- [x] `parseXML`

## 1.3. Entidades nombradas
- [x] Pensar estructura y validarla con el docente
- [x] Implementarla
- [x] Extracción
    - [x] Implementación de heurísticas
- [x] Clasificación
    - [x] Por tópicos
    - [x] Por categorías
- Estadísticas
    - [x] Por tópicos
    - [x] Por categorías
    - [x] Impresión de estadísticas

## 1.4 Limpieza de código
- [x] Pasar un formateador de código
- [x] Revisar TODOs

# 2. Experiencia
Fue nuestra primera vez trabajando en programación orientada a objetos, por lo que fue una experiencia muy fructífera en ese sentido.

# 3. Preguntas
1. Explicar brevemente la estructura de datos elegida para las entidades nombradas.

La estructura de Datos elegida para representar las entidades nombradas se basa en el uso de clases Orientadas a objetos,
la cual permite una organizacion clara y extensible de los diferentes tipos de entidades. Esta estructura esta compuesta por una clase base
o "padre" llamada NamedEntity y varias clases derivadas que representan tipos especificos de entidades.
La clase NamedEntity es la clase de la cual heredan todas las entidades nombradas que contiene los atributos:
Label, Category, Topics y Keywords. (proporciona metodos para acceder a estos atributos y verificar si una palabra clave esta asociada con la entidad
y mostrar las palabras clave)
Mientras que las clases derivadas de NamedEntity (EVENT, LOCATION, ORGANIZATION, PERSON, OTHER)
represetan tipos especificos de entidades nombradas y añaden atributos adicionales para cada tipo.  

2. Explicar brevemente cómo se implementaron las heurísticas de extracción.

Las heurísticas de extracción implementadas se basan en la identificación de patrones lingüísticos comunes que suelen preceder a los nombres propios en el texto. Cada heurística utiliza expresiones regulares para buscar estos patrones y extraer los candidatos de entidades nombradas.
CapitalizedWordHeuristic: Esta heurística busca palabras con mayuscula seguidas de letras en minúsculas, lo que generalmente indica nombres propios. Se utiliza una expresión regular para identificar estos patrones.
IAPrevWordHeuristic: Utiliza una lista de palabras comunes que suelen preceder a los nombres propios, como artículos, preposiciones, adjetivos, títulos de cortesía, etc. La heurística busca estos patrones de palabras precedentes seguidos de palabras capitalizadas.
PrepositionHeuristic: Similar a la anterior, pero se enfoca específicamente en las preposiciones como indicadores de nombres propios. Busca preposiciones comunes seguidas de palabras capitalizadas en el texto.

# 4. IA

Acá nos gustaría comentar como fue nuestro uso de inteligencia artificial en este laboratorio. Se nos ocurrió, para la implementacion de una heurística, preguntarle a ChatGPT cuáles eran las palabras más comunes de anteceder a palabras con mayúsculas. Una vez conocidas estas palabras, las trajimos a nuestro proyecto para la elaboración de un lenguaje regular para extraer candidatos de Named Entities.

# 5. Extras
Aca quisieramos aclarar como en el Lab1 que estuvimos trabajando en su gran mayoria en live Share de visual studio code los 3 integrantes del grupo, hablando tanto como por un grupo de whatsaAp como por discord. Por lo tanto se notara la diferencia de commits (pues siempre los hacia el dueño de live share). Pero garantizamos que trabajamos y entendimos los 3 este lab:)

