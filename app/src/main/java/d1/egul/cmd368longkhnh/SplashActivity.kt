package d1.egul.cmd368longkhnh

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim)
            finish()
        },1500)
    }
}