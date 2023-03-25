package d1.egul.cmd368longkhnh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import d1.egul.cmd368longkhnh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_mainBinding.root)
    }
}