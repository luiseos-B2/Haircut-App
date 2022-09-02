package br.com.haircutapp.presentation.components

import android.content.Context
import android.content.res.ColorStateList
import android.text.InputType
import android.util.AttributeSet
import android.util.MonthDisplayHelper
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.databinding.adapters.TextViewBindingAdapter
import br.com.haircutapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception

class TextField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var textField: TextInputLayout
    private lateinit var textInputEditText: TextInputEditText

    var getTextValue: String? = ""

    var textValue: String? = null
    set(value) {
        field = value
        setText(value)
    }

    var textColor: Int? = null
    set(value) {
        field = value
        if (value != null) {
            setTextColor(value)
        }
    }

    var hintColor: Int? = null
        set(value) {
            field = value
            if (value != null) {
                setHintColor(value)
            }
        }

    var hintValue: String? = null
    set(value) {
        field = value
        setHint(value)
    }

    var inputType: Int? = null
    set(value) {
        field = value
        if (value != null) {
            intToInputType(value)
        }
    }

    var endIconMode: Int? = null
    set(value) {
        field = value
        if (value != null) {
            intToIconMode(value)
        }
    }

    var helperText: String? = null
    set(value) {
        field = value
        setHelperText(value)
    }

    var counterInputLength: Int? = null
    set(value) {
        field = value
        setCounterLength(value)
    }

    init {
        inflate(context, R.layout.text_field_component, this)
        configViews()
        configAttrs(attrs)
        textWatcher()
    }

    private fun configViews() {
        textField = findViewById(R.id.textField)
        textInputEditText = findViewById(R.id.textInputEditText)
    }

    private fun configAttrs(attrs: AttributeSet?) {
        attrs?.let {
            val customAttributesStyle = context.obtainStyledAttributes(attrs, R.styleable.TextField, 0, 0)

            try {
                textValue = customAttributesStyle.getString(R.styleable.TextField_android_text)
                hintValue = customAttributesStyle.getString(R.styleable.TextField_android_hint)
                hintColor = customAttributesStyle.getColor(R.styleable.TextField_hintTextColor, resources.getColor(R.color.black, null))
                textColor = customAttributesStyle.getColor(R.styleable.TextField_android_textColor, resources.getColor(R.color.black, null))
                inputType = customAttributesStyle.getInt(R.styleable.TextField_inputType, 2)
                endIconMode = customAttributesStyle.getInt(R.styleable.TextField_endIconMode, 0)
                helperText = customAttributesStyle.getString(R.styleable.TextField_helperText)
                counterInputLength = customAttributesStyle.getInt(R.styleable.TextField_counterMaxLength, 0)
            } finally {
                customAttributesStyle.recycle()
            }
        }
    }

    private fun setText(text: String?) {
        textInputEditText.setText(text)
    }

    private fun setHint(hint:String?) {
        textField.hint = hint
    }

    private fun setTextColor(color: Int) {
        textInputEditText.setTextColor(color)
    }

    private fun setHintColor(color: Int) {
        textField.hintTextColor = ColorStateList.valueOf(color)
    }

    fun setTextError(text: String?) {
        textField.error = text
        textField.isErrorEnabled = text != null
    }

    private fun setCounterLength(counter: Int?) {
        if (counter != null && counter != 0) {
            textField.counterMaxLength = counter
            textField.isCounterEnabled = true
        }
    }

    @JvmName("setHelperText1")
    private fun setHelperText(helper: String?) {
        textField.helperText = helper
    }

    fun onChangeTextInput(onTextChanged: (String) -> Unit){
        textInputEditText.doOnTextChanged { text, _, _, _ ->
            text?.toString()?.let { onTextChanged.invoke(it) }
        }
    }

    private fun intToInputType(int: Int) {
        textInputEditText.inputType = when (int)
        {
            2 -> InputType.TYPE_CLASS_TEXT
            1 -> InputType.TYPE_CLASS_NUMBER
            else -> InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    private fun intToIconMode(int: Int) {
        textField.endIconMode = when (int)
        {
            3 -> TextInputLayout.END_ICON_DROPDOWN_MENU
            2 -> TextInputLayout.END_ICON_CLEAR_TEXT
            1 -> TextInputLayout.END_ICON_PASSWORD_TOGGLE
            else -> TextInputLayout.END_ICON_NONE
        }
    }

    private fun textWatcher() {
        textInputEditText.doOnTextChanged { text, _, _, _ ->
            getTextValue = text?.toString()
        }
    }

}