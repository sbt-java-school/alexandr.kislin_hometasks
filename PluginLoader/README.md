﻿# alexandr.kislin_hometasks
Домашнее задание по теме ClassLoaders. Цель: создать собсвтенный загрузчик плагинов - структуру, которая может загрузить выбранный .class файл в проект. 

##Выполнение
Для выполнения данной работы понадобилось написать собственный ClassLoader, унаследованный от URLClassLoader из стандартной библиотеки java.net. В нём был переопределён только один метод loadClass, в остальном данный класслоадер подходил в качестве решения данной задачи. Затем был создан интерфейс Plugin, с одним методом, для демонстрации собственной работы, сразу же была добавлена реализация данного интерфейса в классе PluginImpl. После был написан класс PluginManager, в котором и сосредоточена основная работа в рамках данного домашнего задания. Здесь создаётся экземпляр нашего класслоадера, которому передаётся путь до загружаемого плагина. Путь так же хранится во внутреннем поле менеджера. Добавление класса происходит с помощью создания объекта интерфейса Plugin, и вызова переопределённого метода loadClass из созданного ранее собственного класслоадера. В случае успешной загрузки, возвращается объект интерфейса, и его можно использовать в дальнейшем. Класс, загруженный стандартным класслоадером и собственным сравнивались с помощью equals, и оказались не равны, как и должно быть при использование разных класслоадеров.
