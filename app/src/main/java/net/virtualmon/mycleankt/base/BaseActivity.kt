package net.virtualmon.mycleankt.base

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection



private const val PERMISSION_REQUEST = 10


/**
 * Created by AlbertM on 20/04/19.
 */
abstract class BaseActivity : AppCompatActivity() {

    private var permissions = arrayOf(Manifest.permission.INTERNET)

    private lateinit var proDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layout)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissions)) {
                onViewLoaded()
            } else {
                requestPermissions(permissions, PERMISSION_REQUEST)
            }
        } else {
            onViewLoaded()
        }

    }

    abstract var layout : Int

    abstract fun onViewLoaded()

    private fun checkPermission(permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            var allSuccess = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allSuccess = false
                    val requestAgain = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(permissions[i])
                    if (requestAgain) {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Go to settings and enable the permission", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if (allSuccess)
                onViewLoaded()

        }
    }


    fun showLoader(){
        proDialog = ProgressDialog.show(this, "", "Loading...",true)

    }
    fun hideLoader() = proDialog.dismiss()

}