package com.noelchew.library;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.support.v4.content.ContextCompat.getColor;

/**
 * Created by noelchew on 16/01/2017.
 */

public class ExpandableFaqView extends FrameLayout implements View.OnClickListener {

    private static final String TAG = ExpandableFaqView.class.getSimpleName();

    private static final float DEFAULT_TEXT_SIZE_IN_SP = 20;

    /* The default animation duration */
    private static final int DEFAULT_ANIM_DURATION = 250;

    protected RelativeLayout rlQuestionBg, rlAnswerBg;

    protected TextView tvQuestion, tvAnswer;

    protected ImageButton mButton; // Button to expand/collapse

    private boolean mCollapsed = true; // Show short version as default.

    private Drawable mExpandDrawable, mCollapseDrawable, mQuestionBackgroundDrawable, mAnswerBackgroundDrawable;

    private String mQuestionText, mAnswerText;

    private boolean mQuestionUnderlined, mAnswerUnderlined;

    private float mQuestionTextSizeInSp, mAnswerTextSizeInSp;

    private ColorStateList mQuestionTextColor, mAnswerTextColor;

    private int mAnimationDuration;

    private boolean mRelayout;

    private boolean mAnimating;

    /* For saving collapsed status when used in ListView */
    private SparseBooleanArray mCollapsedStatus;
    private int mPosition;

    private int answerHeight;

    public ExpandableFaqView(Context context) {
        this(context, null);
    }

    public ExpandableFaqView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ExpandableFaqView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View view = inflate(getContext(), R.layout.layout_faq, null);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.expandableFaqView);
        mQuestionText = typedArray.getString(R.styleable.expandableFaqView_questionText);
        mAnswerText = typedArray.getString(R.styleable.expandableFaqView_answerText);
        mAnimationDuration = typedArray.getInt(R.styleable.expandableFaqView_animDuration, DEFAULT_ANIM_DURATION);
        mQuestionUnderlined = typedArray.getBoolean(R.styleable.expandableFaqView_questionUnderlined, false);
        mAnswerUnderlined = typedArray.getBoolean(R.styleable.expandableFaqView_answerUnderlined, false);
        mQuestionTextSizeInSp = typedArray.getFloat(R.styleable.expandableFaqView_questionTextSizeSp, DEFAULT_TEXT_SIZE_IN_SP);
        mAnswerTextSizeInSp = typedArray.getFloat(R.styleable.expandableFaqView_answerTextSizeSp, DEFAULT_TEXT_SIZE_IN_SP);
        mQuestionTextColor = typedArray.getColorStateList(R.styleable.expandableFaqView_questionTextColor);
        mAnswerTextColor = typedArray.getColorStateList(R.styleable.expandableFaqView_answerTextColor);
        mQuestionBackgroundDrawable = typedArray.getDrawable(R.styleable.expandableFaqView_questionBackgroundDrawable);
        mAnswerBackgroundDrawable = typedArray.getDrawable(R.styleable.expandableFaqView_answerBackgroundDrawable);
        mExpandDrawable = typedArray.getDrawable(R.styleable.expandableFaqView_expandDrawable);
        mCollapseDrawable = typedArray.getDrawable(R.styleable.expandableFaqView_collapseDrawable);

        if (mExpandDrawable == null) {
            mExpandDrawable = getDrawable(getContext(), R.drawable.ic_expand_more_black_12dp);
        }
        if (mCollapseDrawable == null) {
            mCollapseDrawable = getDrawable(getContext(), R.drawable.ic_expand_less_black_12dp);
        }

        typedArray.recycle();

        addView(view);

    }

    private void findViews() {
        rlQuestionBg = (RelativeLayout) findViewById(R.id.relative_layout_question);
        rlAnswerBg = (RelativeLayout) findViewById(R.id.relative_layout_answer);
        tvQuestion = (TextView) findViewById(R.id.question_text);
        tvAnswer = (TextView) findViewById(R.id.answer_text);
        tvQuestion.setOnClickListener(this);
        tvAnswer.setOnClickListener(this);

        tvQuestion.setTextSize(TypedValue.COMPLEX_UNIT_SP, mQuestionTextSizeInSp);
        tvAnswer.setTextSize(TypedValue.COMPLEX_UNIT_SP, mAnswerTextSizeInSp);

        if (mQuestionTextColor != null) {
            tvQuestion.setTextColor(mQuestionTextColor);
        }

        if (mAnswerTextColor != null) {
            tvAnswer.setTextColor(mAnswerTextColor);
        }

        tvQuestion.setText(mQuestionText);
        tvAnswer.setText(mAnswerText);

        if (mQuestionUnderlined) {
            SpannableString content = new SpannableString(tvQuestion.getText());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            tvQuestion.setText(content);
        }

        if (mAnswerUnderlined) {
            SpannableString content = new SpannableString(tvAnswer.getText());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            tvAnswer.setText(content);
        }

        if (mQuestionBackgroundDrawable != null) {
            rlQuestionBg.setBackground(mQuestionBackgroundDrawable);
        }

        if (mAnswerBackgroundDrawable != null) {
            rlAnswerBg.setBackground(mAnswerBackgroundDrawable);
        }

        mButton = (ImageButton) findViewById(R.id.expand_collapse);
        mButton.setImageDrawable(mCollapsed ? mExpandDrawable : mCollapseDrawable);
        mButton.setOnClickListener(this);

        rlAnswerBg.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        // gets called after layout has been done but before display
                        // so we can get the height then hide the view

//                        answerHeight = rlAnswerBg.getHeight();
                        Log.d(TAG, "onGlobalLayout for: " + mQuestionText);
                        Log.d(TAG, "onGlobalLayout mCollapsed: " + mCollapsed);
                        answerHeight = getAnswerTextViewHeight();

                        Log.d(TAG, "onGlobalLayout answerHeight: " + answerHeight);

                        rlAnswerBg.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        if (mCollapsed) {
//                            rlAnswerBg.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    rlAnswerBg.getLayoutParams().height = 0;
//                                }
//                            });
                            rlAnswerBg.setVisibility(View.GONE);
                        } else {
                            rlAnswerBg.setVisibility(View.VISIBLE);
                        }
                    }

                });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mButton.setImageDrawable(mCollapsed ? mExpandDrawable : mCollapseDrawable);
        if (mCollapsed) {
            if (!mAnimating) {
                rlAnswerBg.setVisibility(View.GONE);
            }
        } else {
            rlAnswerBg.setVisibility(View.VISIBLE);
        }

        if (mRelayout) {
            mRelayout = false;
        }
    }

    @Override
    public void onClick(View view) {
//        if (mButton.getVisibility() != View.VISIBLE) {
//            return;
//        }

        mCollapsed = !mCollapsed;
        mButton.setImageDrawable(mCollapsed ? mExpandDrawable : mCollapseDrawable);

        if (mCollapsedStatus != null) {
            mCollapsedStatus.put(mPosition, mCollapsed);
        }

        // mark that the animation is in progress
        mAnimating = true;

        if (mCollapsed) {
            hideAnswer();
        } else {
            showAnswer();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // while an animation is in progress, intercept all the touch events to children to
        // prevent extra clicks during the animation
        return mAnimating;
    }

    @Override
    protected void onFinishInflate() {
        findViews();
    }

    public void setQuestion(@Nullable CharSequence text) {
        mRelayout = true;
        mQuestionText = text.toString();
        tvQuestion.setText(text);
        setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
    }

    public void setAnswer(@Nullable CharSequence text) {
        mRelayout = true;
        mAnswerText = text.toString();
        tvAnswer.setText(text);
        setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
    }

    public void setQuestion(@Nullable CharSequence text, @NonNull SparseBooleanArray collapsedStatus, int position) {
        mCollapsedStatus = collapsedStatus;
        mPosition = position;
        boolean isCollapsed = collapsedStatus.get(position, true);
        clearAnimation();
        mCollapsed = isCollapsed;
        mButton.setImageDrawable(mCollapsed ? mExpandDrawable : mCollapseDrawable);
        mQuestionText = text.toString();
        setQuestion(text);
//        getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        requestLayout();

        rlAnswerBg.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (rlAnswerBg.getVisibility() != GONE) {
                            answerHeight = getAnswerTextViewHeight();
                        }
                        rlAnswerBg.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        if (mCollapsed) {
                            rlAnswerBg.setVisibility(View.GONE);
                        } else {
                            rlAnswerBg.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    public void setAnswer(@Nullable CharSequence text, @NonNull SparseBooleanArray collapsedStatus, int position) {
        mCollapsedStatus = collapsedStatus;
        mPosition = position;
        boolean isCollapsed = collapsedStatus.get(position, true);
        clearAnimation();
        mCollapsed = isCollapsed;
        mButton.setImageDrawable(mCollapsed ? mExpandDrawable : mCollapseDrawable);
        mQuestionText = text.toString();
        setAnswer(text);
//        getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        requestLayout();

        rlAnswerBg.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (rlAnswerBg.getVisibility() != GONE) {
                            answerHeight = getAnswerTextViewHeight();
                        }
                        rlAnswerBg.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        if (mCollapsed) {
                            rlAnswerBg.setVisibility(View.GONE);
                        } else {
                            rlAnswerBg.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @Nullable
    public CharSequence getQuestion() {
        if (tvQuestion == null) {
            return "";
        }
        return tvQuestion.getText();
    }

    @Nullable
    public CharSequence getAnswer() {
        if (tvAnswer == null) {
            return "";
        }
        return tvAnswer.getText();
    }

    public void setQuestionBackgroundColor(@ColorRes int colorRes) {
        rlQuestionBg.setBackgroundColor(getColor(getContext(), colorRes));
    }

    public void setAnswerBackgroundColor(@ColorRes int colorRes) {
        rlAnswerBg.setBackgroundColor(getColor(getContext(), colorRes));
    }

    public void setQuestionTextSizeInSp(@Dimension int textSizeInSp) {
        mQuestionTextSizeInSp = textSizeInSp;
        tvQuestion.setTextSize(TypedValue.COMPLEX_UNIT_SP, mQuestionTextSizeInSp);
    }

    public void setAnswerTextSizeInSp(@Dimension int textSizeInSp) {
        mAnswerTextSizeInSp = textSizeInSp;
        tvAnswer.setTextSize(TypedValue.COMPLEX_UNIT_SP, mAnswerTextSizeInSp);
    }

    public void setQuestionTextColor(@ColorRes int colorRes) {
        tvQuestion.setTextColor(getColor(getContext(), colorRes));
    }

    public void setAnswerTextColor(@ColorRes int colorRes) {
        tvAnswer.setTextColor(getColor(getContext(), colorRes));
    }

    public void setQuestionUnderlined(boolean questionUnderlined) {
        mQuestionUnderlined = questionUnderlined;
        if (questionUnderlined) {
            SpannableString content = new SpannableString(tvQuestion.getText());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            tvQuestion.setText(content);
        }
    }

    public void setAnswerUnderlined(boolean answerUnderlined) {
        mAnswerUnderlined = answerUnderlined;
        if (answerUnderlined) {
            SpannableString content = new SpannableString(tvAnswer.getText());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            tvAnswer.setText(content);
        }
    }

    public void setQuestionBackgroundDrawable(@DrawableRes int drawableResourceId) {
        mQuestionBackgroundDrawable = getDrawable(getContext(), drawableResourceId);
        rlQuestionBg.setBackground(mQuestionBackgroundDrawable);
    }

    public void setAnswerBackgroundDrawable(@DrawableRes int drawableResourceId) {
        mAnswerBackgroundDrawable = getDrawable(getContext(), drawableResourceId);
        rlQuestionBg.setBackground(mAnswerBackgroundDrawable);
    }

    public void setAnimationDuration(int duration) {
        mAnimationDuration = duration;
    }

    private static boolean isPostHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    private static boolean isPostLolipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static void applyAlphaAnimation(View view, float alpha) {
        if (isPostHoneycomb()) {
            view.setAlpha(alpha);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(alpha, alpha);
            // make it instant
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            view.startAnimation(alphaAnimation);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Drawable getDrawable(@NonNull Context context, @DrawableRes int resId) {
        Resources resources = context.getResources();
        if (isPostLolipop()) {
            return resources.getDrawable(resId, context.getTheme());
        } else {
            return resources.getDrawable(resId);
        }
    }

    private int getAnswerTextViewHeight() {
        boolean gone = rlAnswerBg.getVisibility() == View.GONE;
        if (gone) {
            rlAnswerBg.setVisibility(VISIBLE);
        }
        int textHeight = tvAnswer.getLayout().getLineTop(tvAnswer.getLineCount());
        int padding = tvAnswer.getCompoundPaddingTop() + tvAnswer.getCompoundPaddingBottom();
        if (gone) {
            rlAnswerBg.setVisibility(GONE);
        }
        return textHeight + padding;
    }

    private void showAnswer() {
        if (answerHeight == 0) {
            rlAnswerBg.setVisibility(VISIBLE);
            answerHeight = getAnswerTextViewHeight();
            expand(rlAnswerBg, mAnimationDuration, answerHeight);
        } else {
            expand(rlAnswerBg, mAnimationDuration, answerHeight);
        }
    }

    private void hideAnswer() {
        collapse(rlAnswerBg, mAnimationDuration, 0);
    }

    private void expand(final View v, int duration, int targetHeight) {

//        int prevHeight = v.getHeight();
        int prevHeight = 0;
        v.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mAnimating = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        valueAnimator.setInterpolator(new OvershootInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    private void collapse(final View v, int duration, int targetHeight) {
        int prevHeight = v.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                v.setVisibility(View.GONE);
                mAnimating = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        valueAnimator.setInterpolator(new AnticipateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }
}
