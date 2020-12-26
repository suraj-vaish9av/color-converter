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
Here the second parameter is colorCache size, will keep last 50(in this example, you can set it according to your need) accessed color in memory so that can later accessed fastly.



 
