# WeatherData (example of data acquisition using retrofit2 library from https://openweathermap.org):


[![platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![Kotlin version](https://img.shields.io/badge/Kotlin-1.7.20-blue)]([https://kotlinlang.org/docs/whatsnew16.html](https://kotlinlang.org/docs/whatsnew1720.html))
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)


![](ru.avicorp.weatherdata.png)





###  Used resources weatherData: 


    - Сlean architecture
    - MVVM (ViewModel, ViewModelFactory)
    - viewBinding
    - Coroutines
    - Retrofit2
    - OkHttp
    - Picasso

## How to use

To use it you need to get a token by registering on the resource https:/openweathermap.org.


    private const val CITY_ID = 496638L//city for example - Sergiev Posad
    private const val UNITS = "metric" //unit of measurement
    private const val TOKEN = "439d4b804bc8187953eb36d2a8c26a02" //https:/openweathermap.org.


All required models generated by Data Classes Generated from JSON powered by http://www.json2kotlin.com



### License

 Copyright (C) 2017 The Λrrow Authors

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
