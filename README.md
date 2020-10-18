# DataBindingExamplekt

- [Library Data Binding](https://developer.android.com/topic/libraries/data-binding?hl=id)

**Important** enable `DataBinding` on your project, setup in `gradle`.

```gradle
android {

    ...
    
    //Android Studio Version Until 4
    dataBinding {
        enabled = true
    }
    
    //Android Studio Version 4 -> gradle version 6.1.1 -> android gradle plugin version 4.0.0
    buildFeatures{
        dataBinding = true
    }

}
```

**Add Dependencies** to enable ViewModel.
```gradle
dependencies {
    
    ...
    
    //mvvm
    implementation 'android.arch.lifecycle:viewmodel:1.1.1'
    implementation 'android.arch.lifecycle:livedata:1.1.1'
    implementation 'android.arch.lifecycle:extensions:1.1.1'
}
```

**ViewModel Example**.
```kotlin
import androidx.lifecycle.ViewModel;

class MyViewModel : ViewModel() {
    var str = MutableLiveData<String>()
}
```

#
#### DataBinding On Activity

> activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <variable
            name="viewModel"
            type="com.gzeinnumer.databindingexamplekt.MyViewModel" />
    </data>
    
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:text="@{viewModel.str}"
            android:id="@+id/my_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>
</layout>
```

> MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MyViewModel()
        
        binding.btn.text = "Hallo Zein"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
```
[FullCode](https://github.com/gzeinnumer/DataBindingExamplekt/blob/master/app/src/main/java/com/gzeinnumer/databindingexamplekt/MainActivity.kt)

#
#### DataBinding On Fragment

> fragment_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <variable
            name="viewModel"
            type="com.gzeinnumer.databindingexamplekt.MyViewModel" />
    </data>
    
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:text="@{viewModel.str}"
            android:id="@+id/my_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>
</layout>
```

> MainFragment.kt

```kotlin
class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main)
        binding!!.viewModel = MyViewModel()
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
```
[FullCode](https://github.com/gzeinnumer/DataBindingExamplekt/blob/master/app/src/main/java/com/gzeinnumer/databindingexamplekt/MainFragment.kt)

#
#### DataBinding On AdapterRecyclerView (Single Type)

> item_list.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <variable
            name="viewModel"
            type="com.gzeinnumer.databindingexamplekt.MyViewModel" />
    </data>
    
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:text="@{viewModel.str}"
            android:id="@+id/my_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>
</layout>
```

> AdapterRV.kt

```kotlin
class AdapterRV : RecyclerView.Adapter<AdapterRV.MyHolder>() {
    
    ...

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding: ItemAdapterRvBinding = ItemAdapterRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.viewModel = MyViewModel()
        return MyHolder(binding)
    }

    class MyHolder(itemView: ItemAdapterRvBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemAdapterRvBinding = itemView
        fun bindData(data: String?, onClick: MyOnClick?) {
            binding.text.text = data
            if (onClick != null) {
                binding.text.setOnClickListener { onClick.myOnClick(adapterPosition) }
            }
        }

    }
}

```
[FullCode](https://github.com/gzeinnumer/DataBindingExamplekt/blob/master/app/src/main/java/com/gzeinnumer/databindingexamplekt/AdapterRV.kt)

#
#### DataBinding On AdapterRecyclerView (Multi Type)

> item_list.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <variable
            name="viewModel"
            type="com.gzeinnumer.databindingexamplekt.MyViewModel" />
    </data>
    
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:text="@{viewModel.str}"
            android:id="@+id/my_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>
</layout>
```

> AdapterRV.kt

```kotlin
class AdapterRVMultiType : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    ...

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_NORMAL) {
            val binding: ItemAdapterRvBinding = ItemAdapterRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            binding.viewModel = MyViewModel4()
            MyHolder(binding)
        } else {
            val binding: ItemAdapterRvBinding = ItemAdapterRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            binding.viewModel = MyViewModel4()
            MyHolder(binding)
        }
    }

    class MyHolder(itemView: ItemAdapterRvBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemAdapterRvBinding = itemView
        fun bindData(data: String, onClick: MyOnClick) {
            binding.text.text = data
            binding.text.setOnClickListener { onClick.myOnClick(adapterPosition) }
        }

    }

    companion object {
        private const val TYPE_NORMAL = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position != -1) {
            TYPE_NORMAL
        } else {
            0
        }
    }
}
```
[FullCode](https://github.com/gzeinnumer/DataBindingExamplekt/blob/master/app/src/main/java/com/gzeinnumer/databindingexamplekt/AdapterRVMultiType.kt)

#
#### DataBinding On DialogFragment

> fragment_main_dialog.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <variable
            name="viewModel"
            type="com.gzeinnumer.databindingexamplekt.MyViewModel" />
    </data>
    
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:text="@{viewModel.str}"
            android:id="@+id/my_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>
</layout>
```

> MainDialog.kt

```kotlin
class MainDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main_dialog)
        binding!!.viewModel = MyViewModel3()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
```
[FullCode](https://github.com/gzeinnumer/DataBindingExamplekt/blob/master/app/src/main/java/com/gzeinnumer/databindingexamplekt/MainDialog.kt)

---

```
Copyright 2020 M. Fadli Zein
```