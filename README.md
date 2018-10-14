# This is sample app to test OpenWeather API.
Language used Kotlin and Java.

Architecture used - MVP (Model = CityData.java .  View = CityWeatherFragment.kt .   Presenter = CityWeatherPresenter.kt)
API is called using Retrofit with json format.

For now API key that is used to get data is static which is demo API key provided from OpenWeather. So for any call, data that is returned is same. However you can change this key if you with from strings.xml file.

////////////////////////// Key classes //////////////////////////////

MainActivity.kt === Entry point for this project is MainActivity.kt which inherits BaseActivity.kt. 
As MainActivity.kt activity starts, it loads/replaces frame with fragment CityWeatherFragment.kt. 

CityWeatherFragment.kt === CityWeatherFragment.kt is view class only which is responsible for changing UI states, this class does not contin any business logic. This class inherits BaseFragment.kt and implements to ICityWeatherView.kt class which has some contract methods for view classes. 

CityWeatherPresenter.kt === CityWeatherFragment.kt has reference of CityWeatherPresenter.kt class which is presenter/controller class and it is responsible for all the business logic required to get data from openWeather API. 

CityWeatherPresenter.kt also has reference of CityWeatherFragment.kt class to update views accordingly. This class implements ICityWeatherPresenter.kt class which has some contract methods for presenter classes. 

Other general details are provided as comments in code itself.

Thanks.


