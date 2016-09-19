# alexandr.kislin_hometasks
Домашние задания. Один проект, разные пакеты для каждой задачи.

##Pacage hw3_multimap_generics
Домашняя работа на проверку понимания обощённого программирования и работой с элементами Collection FrameWork. Во время выполнения этой работы освежил знания о коллекшене, и применил вновь полученные знания о дженериках. Проблемы возникали только с логической точки зрения, иногда требовалось перечитывание кода для продолжения правильной логической конструкции.

##Pacage hw5_reflection
Домашняя работа, принесшая мне наибольшее чувство увовлетворения! О рефлексии ранее не знал, но после того как на лекциях Денис выдал материал, освоил эту часть джавы замечательно. Очень интересный инструмент на крайний случай, очень сильный, и очень мне нравится. В работе продемонстрировал многое из того что возможно сделать с помощью рефлексии, использовал StringBuilder, освежил и частично дополнил теоретически знаний ООП (это скорее на лекциях). Особых проблем данная работа не вызвала, единственное что пришлось покапаться с правильной интерпритацией джавадока для BeanUtils.

##Pacage hw6_CachedProxy
В этой работе пришлось использовать ранее не известные мне аннотации, которые я не расценивал как полноценный инструмент языка. Однако, тут увидел, какие типы задач могут решаться с помощью их использования. Во время выполнения научился писать собственные аннотации, использовать их значение, вновь отточил навык использования дженериков, ну и попробовал реализовать объект прокси, написать invocatonHandler для него, так же изучил теоретическую часть построения кэш-структур, порядок генерации ключей, просмотрел типовые примеры реализации кэшей с использованием разных элементов CollectionFramework, и не только с ним. Проблемы вызвала как раз система генерации ключей для кэша, перестраивал её дважды, не мог сразу представить как она должна работать, даже на листочке ручкой рисовал примерную схему.

##Pacage hw_FileAPIAndNIO
Эта домашняя работа носила рекомендательный харрактер, но всё же я закомитил некоторый код, который написал в процессе выполнения данной работы. Освежил знания о классической системе ввода-вывода в Java, сделал некоторые манипуляции, в некоторые моменты чувствовал себя так, будто программирую на чистом C, а не в джаве. Приходилось заботиться о тех местах в программе, которые не относились на прямую к логике исполнения. Затем посмотрел на NIO и NIO2 системы, которые явились логичным развитием классической системы. В них уже вновь почувствовал, что вернулся в ООП-язык. Можно было абстрагироваться от мелких проблем, и использовать готовые методы для разных ситуаций, в которых ранее приходилось "переизобретать велосипед". Познакомился с различными классами и средствами работы с файлами, с директориями, инструментами для манипуляции с данными, освежил теоретические знания на тему абсолютных, нормализованных, относительных путей. В завершение познакомился с такими инструментами, введёнными в NIO и NIO2, как WatchService и FileVisitResult. Реализовал обходчик файлов, о котором задумывался во время работы с классическим IO, но отпугнула сложность и ощущение очередного "переизобретения". Здесь же всё вышло легко, добавил обходчику исключение для .git-директорий, проверял на примере папки со своими проектами.