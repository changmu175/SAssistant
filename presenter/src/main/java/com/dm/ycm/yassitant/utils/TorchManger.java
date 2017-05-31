//package com.dm.ycm.yassitant.utils;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.ImageFormat;
//import android.graphics.PixelFormat;
//import android.graphics.Rect;
//import android.hardware.Camera;
//import android.hardware.camera2.CameraAccessException;
//import android.hardware.camera2.CameraCaptureSession;
//import android.hardware.camera2.CameraCharacteristics;
//import android.hardware.camera2.CameraDevice;
//import android.hardware.camera2.CameraManager;
//import android.hardware.camera2.CaptureRequest;
//import android.hardware.camera2.CaptureResult;
//import android.hardware.camera2.TotalCaptureResult;
//import android.media.ImageReader;
//import android.os.Build;
//import android.os.Handler;
//import android.os.HandlerThread;
//import android.support.v4.app.ActivityCompat;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.LinearLayout;
//
//import java.lang.ref.SoftReference;
//import java.util.Arrays;
//
///**
// * Created by ycm on 2017/5/29.
// * Description:
// * Modified by:
// */
//
//public class TorchManger implements View.OnClickListener, View.OnKeyListener {
//    private String TAG = getClass().getName();
//
//    private WindowManager mWindowManager;
//    private Context mContext;
//    private int width;
//    private int x;
//    private int y;
//    private Bitmap mBitmap;
//    private int mStatusHeight;
//    private WindowManager.LayoutParams mDragParams;
//    private RelativeLayout bgView;
//    private Camera mCamera;
//    ImageView mDragImageView;
//
//    public TorchManger() {
//    }
//
//    public void openTorch(Context context, View v, Bitmap bitmap) {
//
//        this.mContext = context;
//        this.mBitmap = bitmap;
//        if (mWindowManager == null) {
//            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
////            mStatusHeight = WindowInfo.getStatusHeight(mContext); //获取状态栏的高度
//        }
//        Rect r = new Rect();
//        v.getGlobalVisibleRect(r);
//
//        mDragParams = new WindowManager.LayoutParams();
//        mDragParams.format = PixelFormat.TRANSLUCENT;//图片之外其他地方透明
//        mDragParams.gravity = Gravity.LEFT | Gravity.TOP;
//        mDragParams.x = r.left;
//        mDragParams.y = r.top + mStatusHeight;
//        mDragParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//
//        mDragParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        mDragParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        mDragParams.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        ;
//        //mDragParams.dimAmount = 0.7f;
//        mDragImageView = new ImageView(mContext);
//        mDragImageView.setImageBitmap(bitmap);
//        WindowManager.LayoutParams bgLayout = new WindowManager.LayoutParams();
//        bgLayout.format = PixelFormat.TRANSLUCENT;
//        bgLayout.type = WindowManager.LayoutParams.TYPE_PHONE;
//        bgLayout.width = WindowManager.LayoutParams.MATCH_PARENT;
//        bgLayout.height = WindowManager.LayoutParams.MATCH_PARENT;
//        bgLayout.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN |
//                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
//        bgLayout.alpha = 1.0f;
//        bgView = new RelativeLayout(mContext);
//        bgView.setBackgroundColor(Color.parseColor("#da000000"));
//        mWindowManager.addView(bgView, bgLayout);
//
//        mWindowManager.addView(mDragImageView, mDragParams);
//        mDragImageView.setOnClickListener(this);
//        mDragImageView.setOnKeyListener(this);
//        openCamera(mDragImageView);
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v != bgView) {
//            closeTorch();
//        }
//    }
//
//    /**
//     * 关闭手电筒
//     */
//    public void closeTorch() {
//        closeCamera();
//        mWindowManager.removeView(mDragImageView);
//        mWindowManager.removeView(bgView);
//        onClear();
//
//    }
//
//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//        return true;
//    }
//
//    MyCamera2TorchManger mCamera2TorchManger;
//
//    /**
//     * 打开照相机
//     *
//     * @param v
//     */
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public void openCamera(View v) {
//        //适配5.0以上
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mCamera2TorchManger = new MyCamera2TorchManger(bgView, mContext);
//            isOpen = true;
//
//        } else {
//            if (mCamera == null) {
//                mCamera = Camera.open();
//            }
//            camera(mCamera);
//        }
//    }
//
//    private void closeCamera() {
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            if (isOpen) {
//                mCamera2TorchManger.onClose();
//            }
//
//        } else {
//            if (isOpen) {
//                camera(mCamera);
//            }
//        }
//
//    }
//
//    /**
//     * 是否打开的手电筒
//     *
//     * @return
//     */
//    public boolean isOpenTorch() {
//        return isOpen;
//    }
//
//
//    boolean isOpen;
//
//    /**
//     * 打开手电筒
//     */
//    private void camera(Camera camera) {
//        camera.startPreview();
//        Camera.Parameters parameters = camera.getParameters();
//        if (!isOpen) {
//            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//            camera.setParameters(parameters);
//            isOpen = true;
//        } else {
//            isOpen = false;
//            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
//            camera.setParameters(parameters);
//            camera.release();
//            camera = null;
//        }
//    }
//
//    public void onClear() {
//        isOpen = false;
//        if (mBitmap != null && !mBitmap.isRecycled()) {
//            mBitmap.recycle();
//        }
//        mBitmap = null;
//        mContext = null;
//    }
//
//}
//
///**
// * 适配android 5.0+
// *
// * @author YeFeiHu
// */
//@TargetApi(Build.VERSION_CODES.LOLLIPOP)
//class MyCamera2TorchManger implements ImageReader.OnImageAvailableListener {
//    private String TAG = getClass().getName();
//    private CameraManager mCameraManager;
////    private SurfaceView mSurfaceView;
//    ImageReader mImageReader;
//    Handler mHandler;
//    private RelativeLayout parent;
//    private SoftReference<Context> softContext;
//    int mState;
//    CameraCaptureSession mSession;
//    CaptureRequest.Builder preview;
//
//    public MyCamera2TorchManger(RelativeLayout view, Context context) {
//        this.parent = view;
////        mSurfaceView = new SurfaceView(context);
//        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(1, 1);
//        p.setMargins(0, 0, 9, 0);
//        //	p.addRule(RelativeLayout.CENTER_IN_PARENT);
////        mSurfaceView.setLayoutParams(p);
//        //mSurfaceView.setVisibility(View.INVISIBLE);
////        parent.addView(mSurfaceView);
//        this.softContext = new SoftReference<>(context);
//        openCamera2();
//    }
//
//
//    public void onClose() {
//        if (mCameraDevice != null) {
//            mCameraDevice.close();
//            if (mHandler != null)
//                mHandler.removeCallbacksAndMessages(null);
//            mCameraDevice = null;
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private void openCamera2() {
//        mCameraManager = (CameraManager) softContext.get().getSystemService(Context.CAMERA_SERVICE);
//        initCameraAndPreview();
////        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
////
////            @Override
////            public void surfaceDestroyed(SurfaceHolder holder) {
////
////            }
////
////            @Override
////            public void surfaceCreated(SurfaceHolder holder) {
////                initCameraAndPreview();
////            }
////
////            @Override
////            public void surfaceChanged(SurfaceHolder holder, int format, int width,
////                                       int height) {
////
////            }
////        });
//
//
//    }
//
//    private void initCameraAndPreview() {
//        Log.v(TAG, "init camera and preview");
//        HandlerThread handlerThread = new HandlerThread("Camera2");
//        handlerThread.start();
//        mHandler = new Handler(handlerThread.getLooper());
//        try {
//            //       			String[] ids;
//
//            String mCameraId = "" + CameraCharacteristics.LENS_FACING_FRONT;
//            mImageReader = ImageReader.newInstance(640, 480,
//                    ImageFormat.JPEG, 7);
//            mImageReader.setOnImageAvailableListener(this, mHandler);
//
//            if (ActivityCompat.checkSelfPermission(softContext.get(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            mCameraManager.openCamera(mCameraId, DeviceStateCallback, mHandler);
//        } catch (CameraAccessException e) {
//            Log.v(TAG, "open camera failed." + e.getMessage());
//        }
//    }
//
//    CameraDevice mCameraDevice;
//    @SuppressLint("NewApi")
//    private CameraDevice.StateCallback DeviceStateCallback = new CameraDevice.StateCallback() {
//
//        @Override
//        public void onOpened(CameraDevice camera) {
//            mCameraDevice = camera;
//            Log.v(TAG, "DeviceStateCallback:camera was opend.");
//            try {
//                preview = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
////                preview.addTarget(mSurfaceView.getHolder().getSurface());
//                mState = 0;
//                camera.createCaptureSession(
//                        Arrays.asList(mSurfaceView.getHolder().getSurface(),
//                                mImageReader.getSurface()), new MyCameraCaptureSession(), mHandler);
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onDisconnected(CameraDevice camera) {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void onError(CameraDevice camera, int error) {
//
//            Log.v(TAG, "DeviceStateCallback:camera was onError." + error);
//
//        }
//    };
//
//
//    private class MyCameraCaptureSession extends CameraCaptureSession.StateCallback {
//
//        @Override
//        public void onConfigured(CameraCaptureSession session) {
////            Log.v(TAG,"mSessionPreviewStateCallback onConfigured");
////            mSession = session;
//            try {
////                preview.set(CaptureRequest.CONTROL_AF_MODE,
////                		CaptureResult.CONTROL_AE_MODE_ON);
//                //这句控制闪光灯
//                preview.set(CaptureRequest.FLASH_MODE, CaptureRequest.FLASH_MODE_TORCH);
//                session.setRepeatingRequest(preview.build(), new MCameraCaptureSession(), mHandler);
//            } catch (CameraAccessException e) {
//                e.printStackTrace();
//                Log.v(TAG, "set preview builder failed." + e.getMessage());
//            }
//        }
//
//        @Override
//        public void onConfigureFailed(CameraCaptureSession session) {
//            // TODO Auto-generated method stub
//
//        }
//
//    }
//
//    private class MCameraCaptureSession extends CameraCaptureSession.CaptureCallback {
//
//        @Override
//        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request,
//                                       TotalCaptureResult result) {
////            Log.v(TAG,"mSessionCaptureCallback, onCaptureCompleted");
//            mSession = session;
//            checkState(result);
//        }
//
//        @Override
//        public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request,
//                                        CaptureResult partialResult) {
//            Log.v(TAG, "mSessionCaptureCallback, onCaptureProgressed");
//            mSession = session;
//            checkState(partialResult);
//        }
//
//        private void checkState(CaptureResult result) {
//            switch (mState) {
//                case 1:
//                    // NOTHING
//                    break;
//                case 0:
//                    int afState = result.get(CaptureResult.CONTROL_AF_STATE);
//
//                    if (CaptureResult.CONTROL_AF_STATE_FOCUSED_LOCKED == afState ||
//                            CaptureResult.CONTROL_AF_STATE_NOT_FOCUSED_LOCKED == afState
//                            || CaptureResult.CONTROL_AF_STATE_PASSIVE_FOCUSED == afState
//                            || CaptureResult.CONTROL_AF_STATE_PASSIVE_UNFOCUSED == afState) {
//                        //do something like save picture
//                    }
//                    break;
//            }
//        }
//
//
//    }
//
//    @Override
//    public void onImageAvailable(ImageReader reader) {
//
//    }
//
//}
