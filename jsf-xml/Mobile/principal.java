/**
 * 
 * HelloWorld.java
 * The sentinal sample!
 *
 * Copyright © 1998-2008 Research In Motion Ltd.
 * 
 * Note: For the sake of simplicity, this sample application may not leverage
 * resource bundles and resource strings.  However, it is STRONGLY recommended
 * that application developers make use of the localization features available
 * within the BlackBerry development platform to ensure a seamless application
 * experience across a variety of languages and geographies.  For more information
 * on localizing your application, please refer to the BlackBerry Java Development
 * Environment Development Guide associated with this release.
 */

package com.rim.samples.device.helloworlddemo;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;

/*
 * BlackBerry applications that provide a user interface must extend 
 * UiApplication.
 */
class HelloWorldDemo extends UiApplication
{
    /**
     * Entry point for application. 
     */
    public static void main(String[] args)
    {
        // Create a new instance of the application.
        HelloWorldDemo theApp = new HelloWorldDemo();
        
        // To make the application enter the event thread and start processing messages, 
        // we invoke the enterEventDispatcher() method.
        theApp.enterEventDispatcher();
    }

    /**
     * <p>The default constructor. Creates all of the RIM UI components and pushes the
     * application's root screen onto the UI stack.
     */
    private HelloWorldDemo()
    {
        // Push the main screen instance onto the UI stack for rendering.
        pushScreen(new HelloWorldScreen());
    }    
}


/**
 * Create a new screen that extends MainScreen, which provides default standard
 * behavior for BlackBerry applications.
 */
/*package*/ final class HelloWorldScreen extends MainScreen
{

    /**
     * HelloWorldScreen constructor.
     */
    HelloWorldScreen()
    {
        // Add a field to the title region of the screen. We use a simple LabelField 
        // here. The ELLIPSIS option truncates the label text with "..." if the text 
        // is too long for the space available.
        LabelField title = new LabelField("Hello World Demo" , LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        setTitle(title);

        // Add a read only text field (RichTextField) to the screen.  The RichTextField
        // is focusable by default.  In this case we provide a style to make the field
        // non-focusable.
        add(new RichTextField("Hello World!" ,Field.NON_FOCUSABLE));
    }

    /**
     * Display a dialog box to the user with "Goodbye!" when the application 
     * is closed.
     * 
     * @see net.rim.device.api.ui.Screen#close()
     */
    public void close()
    {
        // Display a farewell message before closing application.
        Dialog.alert("Goodbye!");
        System.exit(0);
        
        super.close();
    }   
}
