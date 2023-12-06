# WorkHub
Hриложение для поиска вакансий c использованием API сервиса HeadHunter.

Приложение предоставляет следующую функциональность:

- Поиск вакансий;
- Указание фильтров для поиска;
- Просмотр деталей отдельной вакансии;
- И добавление вакансий в список "Избранного".

## Главный экран -- экран поиска вакансий

На этом экране пользователь может искать вакансии по любому непустому набору слов поискового запроса. Результаты поиска
представляют собой список, содержащий краткую информацию о вакансиях.

### Особенности экрана

- По умолчанию, поиск происходит по всей доступной базе вакансий без учёта региона, отрасли компании и уровня зарплаты и
  валюты.
- Приложение не хранит историю поиска, поэтому между перезапусками приложения текст в поле ввода не обязан сохраняться.
- При вводе нового текста в поле ввода мы осуществляется новый поиск с debounce в 2000 миллисекунд.
- В отдельном элементе списка может быть картинка логотипа компании. В процессе
  загрузки картинки и в случае ошибки загрузки этой картинки показывается плейсхолдер. Также плейсхолдер отображается,
  если информации о картинке нет.
- В зависимости от пришедших с сервера данных информация о вакансии может отображаться несколькими способами:
    - "От XX";
    - "До XX";
    - "От XX до XX";
    - "Зарплата не указана".
- Если в вакансии указана зарплата, то числа отображаются с разбиением на разряды (то
  есть `1 000 000`, `12 345 678`).
- Зарплата в вакансии может быть указана в разной валюте, не только в рублях. Вот полный список возможных валют:
    - Российский рубль (RUR / RUB)
    - Белорусский рубль (BYR)
    - Доллар (USD)
    - Евро (EUR)
    - Казахстанский тенге (KZT)
    - Украинская гривна (UAH)
    - Азербайджанский манат (AZN)
    - Узбекский сум (UZS)
    - Грузинский лари (GEL)
    - Киргизский сом (KGT)
- В целях экономии трафика пользователей загрузка результатов поиска происходит постранично (paging) по 20
  элементов за раз. Запрос на следующую страницу происходит, когда пользователь доскроллил до последнего
  доступного элемента списка.

  <img src ="https://github.com/val-hero/practicum-android-diploma/assets/79761587/ac7f98d1-defd-4016-9b53-88e9b73faa8c" width=30% height=30%> <img src="https://github.com/val-hero/practicum-android-diploma/assets/79761587/509deeec-dae2-4889-a054-8db38109e0f1" width=30% height=30%>

## Фильтрация -- набор экранов фильтров поиска

Используя настройки фильтра, пользователь может уточнить некоторые параметры поиска, который осуществляется на экране
"Поиск". Фильтр позволяет указать:

- Место работы - регион, населённый пункт, указанный в вакансии как рабочая локация.
- Отрасль - сфера деятельности организации, разместившей вакансию.
- Уровень зарплаты - уровень ЗП, соответствующий указанному в вакансии.
- Возможность скрывать вакансии, для которых не указана ЗП.

### Особенности экранов

- Параметры фильтра не являются обязательными - пользователь может уточнить любой параметр из предложенных, а может не
  указывать ничего. В случае, если указан хотя бы один из параметров он должен учитываться при последующих поисковых
  запросах на экране "Поиск". Параметры фильтра, которые пользователь не уточнял, в поисковом запросе не участвуют.
- Настройки параметров фильтра должны сохраняться даже после закрытия приложения.
- Поиск по отраслям компании ведётся сразу по всем элементам дерева отраслей, без разделения на категории по уровням
  вложенности.
- Экраны фильтрации отображаются поверх нижней навигации.
- Если у пользователя выбрана страна поиска вакансий, то список регионов на экране выбора региона поиска
  ограничивается регионами указанной страны.
- Если пользователь выбрал город до выбора страны, то страна подставляется автоматически.
- Кнопка "Сбросить" появляется, если пользователь указал хотя бы одно значение фильтров.
- Кнопка "Применить" появляется, если пользователь указал фильтр, отличающийся от предыдущего.
  Нажатие на кнопку "Применить" приводит к сохранению выбранных настроек фильтра и применению фильтра для всех
  последующих запросов на поиск вакансий до изменения фильтра.
- Все настройки фильтра сохраняются автоматически сразу после изменения.

<img src="https://github.com/val-hero/practicum-android-diploma/assets/79761587/edc05d44-9383-4567-9f86-785885b67ee2" width=30% height=30%>


## Экран просмотра деталей вакансии

Нажав на элемент списка найденных вакансий (а так же в списке закладок и похожих вакансий), пользователь попадает на
экран с подробным описанием вакансии. Помимо уровня ЗП, требуемого опыта и графика работы пользователь может на этом
экране увидеть:

- Информацию о работодателе
- Подробное описание вакансии
- Перечень требуемых ключевых навыков
- Контактную информацию

Также пользователь может ознакомиться со списком похожих вакансий, поделиться ссылкой на данную вакансию, а также
связаться с работодателем через указанные контакты.

### Особенности экрана

- Любая часть описания деталей вакансии опциональна, то есть из сети может не прийти какое-то из ожидаемых полей. В этом
  случае программа должна корректно работать и отображать те данные, которые у неё есть.
- Подробное описание вакансии приходит в HTML-формате, напрямую отобразить полученное по сети поле не получится.
- При нажатии на элемент списка похожих вакансий требуется открыть новый экран деталей вакансий, который связан с
  предыдущим (на предыдущий экран можно вернуться, нажав кнопку `Back`).
- Отображение указанной зарплаты и валюты должно происходить аналогично выдаче поиска вакансий.
- При нажатии на указанный адрес электронной почты должен открываться диалог с предложением написать email на этот
  адрес.
- При нажатии на указанный номер телефона приложение должно открыть приложение для звонка, в котором уже будет отображён
  номер телефона.
- Отображение списка похожих вакансий должно происходить аналогично отображению списка вакансий на экране поиска.
- Экраны деталей вакансии отображаются поверх нижней навигации.

<img src="https://github.com/val-hero/practicum-android-diploma/assets/79761587/756ec241-72e4-465b-af8e-dd92db1ae6f4" width=30% height=30%>
  
<img src="https://github.com/val-hero/practicum-android-diploma/assets/79761587/be16a991-4f52-4560-87fa-dfaa5c6896e2" width=30% height=30%>


## Экран избранных вакансий

Пользователь может добавлять вакансии в "Избранное", чтобы иметь возможность быстро вернуться к заинтересовавшему его
предложению. Добавить вакансию в "избранное" (или удалить из "избранного") можно на экране "Вакансия". На экране списка
избранных вакансий пользователь может удалить вакансию из закладок. Все вакансии, добавленные в закладки, можно увидеть
на отдельном экране в приложении.

### Особенности экрана

- Вакансии, добавленные в "избранное" можно просматривать без подключения к интернету. Если нет интернета не отображается список похожих вакансий.
- Если пользователь добавляет вакансию в закладки, она появляется на экране списка закладок.
<img src="https://github.com/val-hero/practicum-android-diploma/assets/79761587/b866e90a-128c-4cb7-b929-a7c96e415fb9" width=30% height=30%>


## Экран информации о команде разработчиков

На экране отображается статический список людей, участвовавших в разработке приложения. 
<img src="https://github.com/val-hero/practicum-android-diploma/assets/79761587/ec24661d-65e4-4c46-8bc9-75bee92f1ca7" width=30% height=30%>

## Общие требования

- Приложение поддерживает устройства, начиная с Android 8.0 (minSdkVersion = 26)
- Приложение поддерживает только портретную ориентацию (`portrait`), при перевороте экрана ориентация не меняется.

