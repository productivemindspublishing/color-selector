# Color Selector

## A practical colour selector library for android, by ProductiveMinds

### Objective
The objective of this project is to provide a plugable and reusable 'colour selection' library (module) for anyone who requires a custom color selector for their Android projects. 
While developing an Android project, the developer of this library did not find a suitable library. Therefore, started this project from ground up. The library source is provided under [Apache 2 LICENSE](https://www.apache.org/licenses/LICENSE-2.0) - so you are welcome to explore and implement as you please.


### Content and Minimum Requirement
1. Java classes
2. Layout files
3. Drawable resources
4. Supported Minimum SDk => version 21 (aka LOLLIPOP)

- ColorSelector.java class is the wrapper class that extends Android's Color.java. You are unlikely to refer to this class unless you are customising.
- ColorSliderUi.java class - you will be using this class in your project. The class calls the colour selector popup and is responsible returning the colour code for the selector colour.

### Installation
- Download or clone the project
- Open in Android Studio or your favourite IDE
- Add to your project, as you please. For example, in Android Studio, you could import as a new module, then reference in your project, as described below.

#### Example installation
1. Start Android Studio
2. At the top menu, choose, File => New => Import Module...
3. Browse to the location of your download, and select the project
4. For module, 'color_selector', leave the option as checked for 'Import'
5. Click 'Finish'
6. Android Studio will add the library to your project
7. Open the 'build.gradle' file for your main project, add the following dependency...
8. Implementation project(':color_selector')
9. Sync your project
10. You are now ready to implement, see below for an implementation example


### Implementation
Once installed, in your project, instantiate and call the 'showDialogColorSelector' method of the ColorSliderUi.java class. 

#### Example Implementation Steps
```java
ColorSliderUi colorSliderUi = new ColorSliderUi();
colorSliderUi.showDialogColorSelector(MyActivity.this, true, true, "Select Text colour");
```
Above, the five parameters are
1. The activity class calling the library.
2. The title of the popup
3. If true, the hex code label will be visible in the popup.
4. If true, user can close the popup by clicking anywhere on the screen. If false, the popup can only be closed by clicking appropriate buttons.
5. If true, show slider for alpha channel, otherwise only show 3 sliders (for rgb channels).


### Applying a selected colour - 2 methods available

#### Method 1, handle the triggered interface, as demonstrated below...
```java
colorSliderUi.setOnColorSelectedListener(new ColorSliderUi.OnColorSelectedListener() {
    @Override
    public void getOnColorStringSelectedListener(String color) {
    }

    @Override
    public void getOnColorIntSelectedListener(int color) {
        sample_textview.setTextColor(ColorStateList.valueOf(color));
    }

    @Override
    public void getOnColorAlphaSelectedListener(int color) {

    }
    @Override
     public void getOnColorIntSelectedBtnClickedListener(int color) {
        sample_textview.setTextColor(ColorStateList.valueOf(color));
    }
});
```

Above, the four interfaces return values as follows: 
1. The hex code, 
2. The int equivalent of the hex code, 
3. The alpha value for the 'A' slider, and 
4. The int equivalent of the hex code. 

Note that both second and fourth interfaces return the int value of the selected color. The second interface sends a response everytime the user move the slider, while the fourth interface only returns value, when (or if) the user clicks the check (done) button.

As the fourth interface only send a response on final click, you can either update the view upon each slider move or only when user is completes the selection process.

#### Method 2, retrieve saved values in the shared preference
If not using the interfaces, the plugin provides corresponding key/value pairs in the shared preference, as shown below...
```java
UserPreferencesManager.setStringPrefValue(context,
        ProjectConstants.C_S_HEX_LATEST, hexCode);
UserPreferencesManager.setIntPrefValue(context,
        ProjectConstants.C_S_ARGB_COLOR_LATEST, ColorSelector.COLOR_ENCODED);
```


Above, shared preference keys are set for the hex code, and also the alpha.
You can use the hex code, for example, by calling..
```java
String hexCodeColor = UserPreferencesManager.getStringPrefValue(context,
        ProjectConstants.C_S_HEX_LATEST, hexCode);
sample_textview.setTextColor(ColorStateList.valueOf(Color.parseColor(color)));
```


The interfaces and shared preference values are set in ColorSliderUi.java.
Each method described above will set the color of the textview, sample_textview, to the selected colour.


### Screenshots
![Screenshot 1](screenshots/screenshot_1.jpg)
![Screenshot 1](screenshots/screenshot_2.jpg)


You are welcome to initiate contact for comments or suggestions - productivminds@gmail.com




