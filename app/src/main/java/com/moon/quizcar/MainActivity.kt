package com.moon.quizcar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.moon.quizcar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.word.setOnClickListener {
            startActivity(Intent(this, WordActivity::class.java))
        }

        binding.otherGameImage.setOnClickListener {
            Toast.makeText(this, "준비중입니다!", Toast.LENGTH_SHORT).show()
        }
        binding.otherGame.setOnClickListener {
            Toast.makeText(this, "준비중입니다!", Toast.LENGTH_SHORT).show()
        }

        binding.review.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$packageName")
                )
            )
        }
        binding.reviewImage.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$packageName")
                )
            )
        }

        binding.purchase.setOnClickListener {
            MaterialAlertDialogBuilder(this).apply {
                setView(
                    LayoutInflater.from(this@MainActivity)
                        .inflate(R.layout.dialog_purchase, null, false)
                )
                setTitle("구매")
                setMessage("원하는 상품을 구매해주세요.")
                setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
                show()
            }

        }
        binding.purchaseImage.setOnClickListener {
            MaterialAlertDialogBuilder(this).apply {
                setView(
                    LayoutInflater.from(this@MainActivity)
                        .inflate(R.layout.dialog_purchase, null, false)
                )
                setTitle("구매")
                setMessage("원하는 상품을 구매해주세요.")
                setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
                show()
            }
        }


//        binding.clear.setOnClickListener {
//            var pref = getSharedPreferences("quiz", MODE_PRIVATE)
//            pref.edit().putInt("stage", 1).commit()
//            updateStage()
//        }

        MobileAds.initialize(this) {}
        binding.adView.run {
            loadAd(AdRequest.Builder().build())
        }
    }

    override fun onResume() {
        super.onResume()
        updateStage()
    }

    private fun updateStage() {
        var pref = getSharedPreferences("quiz", MODE_PRIVATE)
        val stage = pref.getInt("stage", 1)
        binding.stage.text = "최고단계: $stage"
    }
}