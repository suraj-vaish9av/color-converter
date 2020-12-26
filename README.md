# color-converter

[![](https://jitpack.io/v/suraj-vaish9av/color-converter.svg)](https://jitpack.io/#suraj-vaish9av/color-converter)


color converter has a list of 18K+ colors, which lets you convert the color name to hex and vice-versa.


# Download
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
Step 2. Add the dependency
```
	dependencies {
	        implementation 'com.github.suraj-vaish9av:color-converter:0.1.0'
	}
```
  
# How to use it?
In your Application's onCreate method, add this:
```
ColorConverter.configure(applicationContext)  
```

OR

```
ColorConverter.configure(applicationContext,50)
```
Here the second parameter is cacheSize, which will keep the last 50(in this example, you can set it according to your need) accessed color in memory so that it can later be accessed.

<h3>How to convert, color name to hex and vice-versa?</h3>

If you have the name of color and what to get the hex code:

```
val hexCode = "Alabaster".toHex()     // will return #f3e7db
```

If you have the hex code and want to get the color name:

```
val colorName = "#f3e7db".toColorName() // will return Alabaster
```

that's it, but wait a minute what if you neither have the color name nor the color code, will this library be useful for you?
Yes, there are two methods for this:

```
suspend fun getRandomColor(): Color?
```
returns only one random color

and 

```
fun getRandomColors(size:Int): LiveData<List<Color>>
```
returns LiveData of random colors of the given size


Now the ```Color``` is a class that contains: ```id, colorName, hexCode```.
Use these methods wherever that suits you or your project.


# Sample App

In the sample app, Inspired by the artwork of [Piet Mondrian](https://en.wikipedia.org/wiki/Piet_Mondrian): [Tableau I](https://www.google.com/search?tbm=isch&q=tableau%20i%20by%20piet%20mondrian), I have created an activity with some random colors that looks like this:

<img src="https://github.com/suraj-vaish9av/color-converter/blob/main/img/color-converter.gif" width="30%" />


| Method  | Caching | Coroutines | Description |
| ------------- | ------------- | ------------- | ------------- |
| ```String.toColorName()```  | Yes, If applied  | Yes  | Accepts a hex-code and returns color name.  |
| ```String.toHex()```  | Yes, If applied  | Yes  | Accepts color name and returns hex-code.  |
| ```getRandomColor()```  | No  | Yes  | Returns only one random color.  |
| ```getRandomColors(size:Int)```  | No  | Using, internally  | Returns random colors of the given size.  |
