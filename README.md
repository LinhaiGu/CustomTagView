# CustomTagView

![](https://raw.githubusercontent.com/LinhaiGu/imagefile/master/20190430182508.jpg)

Step 1. Add the JitPack repository to your build file

* gradle

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
	        implementation 'com.github.LinhaiGu:CustomTagView:Tag'
	}
```

Step 3. How to use


```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

    <com.boohee.customtagview.CustomTagView
            android:id="@+id/custom_view"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            android:textSize="17sp"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>
	
    <com.boohee.customtagview.CustomTagView
            android:id="@+id/custom_view2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            android:textSize="17sp"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toBottomOf="@id/custom_view"
            android:layout_marginTop="20dp"/>
</android.support.constraint.ConstraintLayout>
```



MainActivity:

```java
public class MainActivity extends AppCompatActivity {

    private CustomTagView custom_view;
    private CustomTagView custom_view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        custom_view=findViewById(R.id.custom_view);
        custom_view.setLayoutResource(R.layout.item_tag);
        custom_view.setContentAndTag("商品名称，最棒的产品","特价甩卖");

        custom_view2=findViewById(R.id.custom_view2);
        custom_view2.setLayoutResource(R.layout.item_tag2);
        custom_view2.setContentAndTag("商品名称，最棒的产品，快来购买","新品推荐");
    }
}
```

item_tag:

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical">

    <TextView
            android:id="@+id/tv_tag"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:layout_marginLeft="1dp"
            android:paddingTop="2dp"
            android:paddingBottom="2dp"
            android:background="@drawable/shape_tag"
            android:gravity="center"
            android:paddingLeft="6dp"
            android:paddingRight="6dp"
            android:text="标签"
            android:layout_marginBottom="2dp"
            android:textColor="@android:color/white"
            android:textSize="12sp"/>

</LinearLayout>
```

