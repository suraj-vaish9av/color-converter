# color-converter

[![](https://jitpack.io/v/suraj-vaish9av/color-converter.svg)](https://jitpack.io/#suraj-vaish9av/color-converter)


color converter has a list of 18K colors, which lets you convert the color name to hex and vice-versa.


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
In your application class's onCreate method, add this:
```
ColorConverter.configure(applicationContext)  
```

OR

```
ColorConverter.configure(applicationContext,50)
```
Here the second parameter is colorCache size, which will keep the last 50(in this example, you can set it according to your need) accessed color in memory so that it can later be accessed fastly.

Now the key part:

If you have the name of color and what to get the hex code, just do this:

```
val hexCode = "Alabaster".toHex()     // will return #f3e7db
```

If you have the hex code and want to get the color name, do this:

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

In the sample app, By inspiring the artwork of Piet Mondrian: Tableau I, I have created a screen that looks like this:

![Inspired by Tableau I by Piet Mondrian](https://github.com/suraj-vaish9av/color-converter/blob/main/img/color-converter.gif)
