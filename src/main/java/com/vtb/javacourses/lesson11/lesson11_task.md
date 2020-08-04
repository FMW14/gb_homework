1. Создать класс, который может запускать (выполнять) «тесты».
1. В качестве тестов выступают классы с наборами методов, снабженных аннотациями @Test. Класс, запускающий тесты, должен иметь статический метод start(Class testClass), которому в качестве аргумента передается объект типа Class. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если он присутствует. Далее запускаются методы с аннотациями @Test, а по завершении всех тестов – метод с аннотацией @AfterSuite.
1. К каждому тесту необходимо добавить приоритеты (int-числа от 1 до 10), в соответствии с которыми будет выбираться порядок их выполнения. Если приоритет одинаковый, то порядок не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре. Если это не так – необходимо бросить RuntimeException при запуске «тестирования».