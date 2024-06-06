package com.control.paymentlibrery.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.control.paymentlibrery.databinding.ItemImageFoundBinding;

public class BackgroudHome extends ConstraintLayout {
    private ItemImageFoundBinding binding;

    public BackgroudHome(@NonNull Context context) {
        super(context);
        initialize(context);
    }

    public BackgroudHome(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public BackgroudHome(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public BackgroudHome(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    private void initialize(Context context) {
        binding = ItemImageFoundBinding.inflate(LayoutInflater.from(context), this, true);

    }

    public ItemImageFoundBinding getBinding() {
        return binding;
    }
}
