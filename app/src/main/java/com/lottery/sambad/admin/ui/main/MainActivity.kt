package com.lottery.sambad.admin.ui.main

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.lottery.sambad.admin.R
import com.lottery.sambad.admin.databinding.ActivityMainBinding
import com.lottery.sambad.admin.ui.ads_image.AdsImageUploadActivity
import com.lottery.sambad.admin.ui.lottery_number_upload.LotteryNumberUploadActivity
import com.lottery.sambad.admin.ui.pdf_and_image_upload.PdfAndImageUploadActivity
import com.lottery.sambad.admin.ui.tutorial.HomeTutorialUploadActivity
import com.lottery.sambad.admin.utils.MyExtensions.shortToast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initAll()

        checkRequiredPermission()



    }


    private fun initAll() {
        binding.uploadPdfAndImageButton.setOnClickListener(this)
        binding.uploadLotteryNumberButton.setOnClickListener(this)
        binding.uploadAdsImageButton.setOnClickListener(this)
        binding.uploadHomeTutorialButton.setOnClickListener(this)
    }

    private fun checkRequiredPermission() {
        Dexter.withContext(this)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    shortToast(resources.getString(R.string.need_to_grant_all_permission))
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    p1?.continuePermissionRequest()
                }

            })
            .check()
    }

    override fun onClick(v: View?) {
        v?.let {
            var uploadIntent: Intent
            when (it.id) {
                R.id.uploadPdfAndImageButton -> {
                    uploadIntent= Intent(this,PdfAndImageUploadActivity::class.java)
                    startActivity(uploadIntent)
                }

                R.id.uploadLotteryNumberButton -> {
                    uploadIntent= Intent(this,LotteryNumberUploadActivity::class.java)
                    startActivity(uploadIntent)
                }

                R.id.uploadAdsImageButton -> {
                    uploadIntent= Intent(this,AdsImageUploadActivity::class.java)
                    startActivity(uploadIntent)
                }

                R.id.uploadHomeTutorialButton -> {
                    uploadIntent= Intent(this,HomeTutorialUploadActivity::class.java)
                    startActivity(uploadIntent)
                }
            }
        }
    }


}