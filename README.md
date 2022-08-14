This is a simple app that displays news articles and and stock tickers. 

This app follows and MVVM architecture, and these are the libraries used.

Kotlin coroutines -  This is a ligthweight async programming framework. 
Coroutines do not require any external API and are natural to Kotlin
language, in comparison to RxJava. 

Hilt - I used hilt for dependancy injection, it is a Google maintained 
library for dependancy injection. Whie Koin is a vaiable option, Google recommends
Hilt for Android applications, this is huge plus in terms of community support and
documentation. 

Retrofit - This app makes use of Retrofit for nerworking purposes. It is light wieght and easier to
use in comparison to Volley. eg auto Json to Kotlin/Java convertsion.

Glide - This library is used to manage media used on the app, it helps load remote image, memory management
and caching. Compared to Piacasso, Glide had better laoding times and more efficient memory management.

Lottie - This library is used to display the animation on the Error screen. Lotties allows usage of JSON
based animations, which are very small in memory compare to other forms of media.

Navigation componemt - For navigation, the app uses the Jetpack nevigation component. This abstracts away complex logi
associated with naigation on Android, managing Fragment transactions, the back stack etc...

JUnit - Is used to unit testing, it is open source and well popular and allows easy testig and verification by makign use 
of common assertions
