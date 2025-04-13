This APP creates the UI element on Demand from the user, and then add to the layout.


## What is a TextWatcher?

**TextWatcher** is an interface provided by the Android SDK (in the package `android.text`) that lets you listen for changes to the text in an editable view, such as an `EditText`. It works based on the *observer pattern*, which is a common design pattern where an object (in this case, the EditText) notifies registered observers (TextWatcher implementations) when a change occurs.

---

## The Methods of TextWatcher

When you implement a TextWatcher, you need to override three methods:

1. **`beforeTextChanged(CharSequence s, int start, int count, int after)`**  
   - **When it’s called:** Right before the text in the EditText changes.
   - **Parameters:**
     - `s`: The current text before the change.
     - `start`: The starting index where the change is going to occur.
     - `count`: The number of characters that are about to be replaced.
     - `after`: The number of new characters that will be added.
   - **Use case:** You might use this method for actions such as saving the previous state or preparing for a transformation.

2. **`onTextChanged(CharSequence s, int start, int before, int count)`**  
   - **When it’s called:** As the text is being changed.
   - **Parameters:**
     - `s`: The text after the change has been applied.
     - `start`: The starting index where the change occurred.
     - `before`: The length of the old text that was replaced.
     - `count`: The length of the new text that was added.
   - **Use case:** Often used for immediate feedback as the user types, for example, enabling/disabling a button based on input length.

3. **`afterTextChanged(Editable s)`**  
   - **When it’s called:** Immediately after the text has been changed.
   - **Parameters:**
     - `s`: The final text after the change.
   - **Use case:** This is where you usually perform validation, formatting, or update other UI elements. It’s the most popular callback to react once the user is done with a change.

---

## How to Attach a TextWatcher

In Java, you’d typically write something like this:

```java
editText.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Code executed before text change
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Code executed as text changes
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Code executed after text change
    }
});
```

In Kotlin, thanks to lambdas and extension functions, you can simplify your code. For instance, using Kotlin’s [KTX](https://developer.android.com/kotlin/ktx) libraries:

```kotlin
editText.addTextChangedListener {
    // This lambda is typically a simplified abstraction for afterTextChanged.
    // If you need more control, you can implement all three methods as needed.
    afterTextChanged { editable ->
        // Do something with editable
    }
}
```

Or, define your own TextWatcher object, like:

```kotlin
val watcher = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // For example, enable a button if both fields are not empty
        if (s?.isNotEmpty() == true /* && check the other field */) {
            createButton.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.green))
        }
    }

    override fun afterTextChanged(s: Editable?) {}
}

editText.addTextChangedListener(watcher)
```

---

## Why Use a TextWatcher?

- **Immediate Feedback:** TextWatcher allows your app to respond immediately as the user inputs text. This is perfect for validations, hints, or enabling/disabling UI actions.
- **Data Validation:** You can check input lengths, formats (like phone numbers or email addresses), or even filter input in real time.
- **Dynamic UI Updates:** As shown in your example, you can change the background of a button or trigger other dynamic changes based on user input.

---

## Where Does It Come From?

TextWatcher has been part of the Android SDK since early versions. It was designed to work with the editable text components on Android (like EditText) to provide granular control over text changes. Since it’s a fundamental part of building interactive, responsive UIs, it’s considered a basic but essential tool for any Android developer.

---

## Other Observer Patterns in Android

TextWatcher is just one example of the observer pattern in Android. Others include:
- **View.OnClickListener:** Listen for button clicks.
- **RecyclerView.AdapterDataObserver:** Monitor changes in the adapter.
- **LiveData Observers (in Android Architecture Components):** React to changes in data streams.

These patterns allow your app to be reactive—responding to changes, which is a cornerstone of modern, interactive apps.

---


## TL;DR

- **TextWatcher** is an Android interface that listens for changes in text fields.
- It provides three methods (`beforeTextChanged`, `onTextChanged`, `afterTextChanged`) for different moments of text change.
- It’s a prime example of the observer pattern in Android.

