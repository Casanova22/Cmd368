package d1.egul.cmd368longkhnh

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import d1.egul.cmd368longkhnh.databinding.FragmentFirstBinding
import d1.egul.cmd368longkhnh.service.NetworkHandlerCmd
import d1.egul.cmd368longkhnh.view.ViewModelCmd

class FragmentFirst : Fragment() {

    private var _firstBinding : FragmentFirstBinding? = null
    private val binding get() = _firstBinding!!

    private var delay = Handler()
    private val args = Bundle()
    private lateinit var viewModel: ViewModelCmd
    private var checkInternetConnection = NetworkHandlerCmd()
    private var checknet = false

/*    private val textView1 = binding.b1
    private val textView2 = binding.b2
    private val textView3 = binding.b3*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        _firstBinding = FragmentFirstBinding.inflate(inflater,container,false)

        binding.tabLogo1.alpha = .7F
        binding.tabLogo2.alpha = .7F
        binding.tabLogo3.alpha = .7F

        viewModel = ViewModelProvider(this)[ViewModelCmd::class.java]
        animStart()
        connectionCheck()
        return binding.root
    }

    private fun animStart() {

        val buttons = arrayOf(binding.btn1, binding.btn2, binding.btn3)
        var i = 1

        for (viewId in buttons) {

            val fadeAnim = AnimationUtils.loadAnimation(context, R.anim.field_name_anim)
            fadeAnim.startOffset = (i * 200).toLong()
            val textViewId = buttons[i - 1]
            textViewId.startAnimation(fadeAnim)
            i++
        }
        val anim = AnimationUtils.loadAnimation(context, R.anim.top_down)
        binding.logo.startAnimation(anim)
    }

    private fun connectionCheck() {
        checknet = checkInternetConnection.connectionError(requireActivity())
        if (checknet) {
            viewModel.initJson()
            buttonCode()
        } else {
            Toast.makeText(context, "PLEASE CHECK YOUR INTERNET CONNECTION!!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun buttonCode() {
        val scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up)
        val scaleDown = AnimationUtils.loadAnimation(context, R.anim.to_right)

        viewModel.dataListModel.observe(viewLifecycleOwner){
            it.let {
                if (it != null) {
                    for (i in it.indices) {
                        val packageName = it[i].PathName
                        val webView = it[i].Url
                        val jumpCode = it[i].Status
                        if (packageName == context?.packageName) {
                            Log.e(ContentValues.TAG, packageName.toString())
                            when (jumpCode) {
                                true -> {
                                    binding.b1.text = it[i].RegisterKey
                                    binding.b2.text = it[i].LoginKey
                                    args.putBoolean("code", true)
                                    args.putString("urlview", webView)
                                }
                                else -> {
                                    binding.b1.text = getString(R.string.b1)
                                    binding.b2.text = getString(R.string.b2)
                                    args.putBoolean("code", false)
                                }
                            }

                            binding.btn1.setOnTouchListener { _, event ->
                                if (event.action == MotionEvent.ACTION_DOWN) {
                                    binding.btn1.startAnimation(scaleUp)
                                } else if (event.action == MotionEvent.ACTION_UP) {
                                    binding.btn1.startAnimation(scaleDown)
                                    delay.postDelayed({
                                        args.putString("title", "webView")
                                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,
                                            args)
                                    },200)
                                }
                                return@setOnTouchListener true
                            }

                            binding.btn2.setOnTouchListener { _, event ->
                                if (event.action == MotionEvent.ACTION_DOWN) {
                                    binding.btn2.startAnimation(scaleUp)
                                } else if (event.action == MotionEvent.ACTION_UP) {
                                    binding.btn2.startAnimation(scaleDown)
                                    delay.postDelayed({
                                        args.putString("title", "webView")
                                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,
                                            args)
                                    },200)

                                }
                                true
                            }

                            binding.btn3.setOnTouchListener { _, event ->
                                val toGame = Intent(context, GameViewActivity::class.java)

                                if (event.action == MotionEvent.ACTION_DOWN) {
                                    binding.btn3.startAnimation(scaleUp)
                                } else if (event.action == MotionEvent.ACTION_UP) {
                                    binding.btn3.startAnimation(scaleDown)
                                    delay.postDelayed({
                                        startActivity(toGame)
                                    },200)
                                }
                                true
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _firstBinding = null
    }
}