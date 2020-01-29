package com.example.bananaskin.ui.home;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bananaskin.MainActivity;
import com.example.bananaskin.NavDrawerAct;
import com.example.bananaskin.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public ImageView imageView;
    public Button imgBtn;
    public Button submitBtn;
    public SeekBar sBar;
    public TextView tView;
    public SeekBar sBar2;
    public TextView tView2;
    public SeekBar sBar3;
    public TextView tView3;
    public TextView enteredpw;
    public Menu menu;
    public MenuItem menuItem;
    public boolean inBed = false;
    public ChipGroup chipGroup;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        enteredpw = (TextView) root.findViewById(R.id.mark);



        Spinner concerningSpinner = (Spinner) root.findViewById(R.id.concerning);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.concerning, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        concerningSpinner.setAdapter(adapter);

        Spinner causeSpinner = (Spinner) root.findViewById(R.id.cause);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> causeAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.cause, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        causeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        causeSpinner.setAdapter(causeAdapter);

        // for image button
        imgBtn = (Button) root.findViewById(R.id.imageButton);

        submitBtn = (Button) root.findViewById(R.id.submitButton);

        //Test code for image functionality

        imageView = (ImageView) root.findViewById(R.id.showImage);

        selectImage(getContext(),imgBtn);

        Spinner impactBusinessSpinner = (Spinner) root.findViewById(R.id.impactedBusinessSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> impactBusinessAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.impactedBusinessSpinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        impactBusinessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        impactBusinessSpinner.setAdapter(impactBusinessAdapter);

        Spinner impactFunctionsSpinner = (Spinner) root.findViewById(R.id.impactedFunctionsSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> impactFunctionsAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.impactedFunctionsSpinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        impactFunctionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        impactFunctionsSpinner.setAdapter(impactFunctionsAdapter);

        sBar = (SeekBar) root.findViewById(R.id.impactedClientsBar);
        tView = (TextView) root.findViewById(R.id.impactedClientsCount);
        tView.setText(sBar.getProgress() + "/" + sBar.getMax());
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView.setText(pval + "/" + seekBar.getMax());
            }
        });

        sBar2 = (SeekBar) root.findViewById(R.id.impactedEmployeesBar);
        tView2 = (TextView) root.findViewById(R.id.impactedEmployeesCount);
        tView2.setText(sBar2.getProgress() + "/" + sBar2.getMax());
        sBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView2.setText(pval + "/" + seekBar.getMax());
            }
        });

        Spinner reputationalImpactSpinner = (Spinner) root.findViewById(R.id.reputationalImpactSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> reputationalImpactAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.reputationalImpactSpinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        reputationalImpactAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        reputationalImpactSpinner.setAdapter(reputationalImpactAdapter);

        Spinner physicalDamageSpinner = (Spinner) root.findViewById(R.id.physicalDamageSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> physicalDamageAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.physicalDamageSpinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        physicalDamageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        physicalDamageSpinner.setAdapter(physicalDamageAdapter);

        sBar3 = (SeekBar) root.findViewById(R.id.estimatedLossAmountBar);
        tView3 = (TextView) root.findViewById(R.id.estimatedLossAmountCount);
        tView3.setText(sBar3.getProgress() + "/" + sBar3.getMax());
        sBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView3.setText(pval + "/" + seekBar.getMax());
            }
        });



        //Code for Submit Button Email

        root.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pw = enteredpw.getText().toString();
                if(pw.equals("10"))
                {
                    enteredpw.setText("20");
                }
                else if (pw.equals("20")) {
                        enteredpw.setText("30");
                 }
                else if (pw.equals("30")) {
                    enteredpw.setText("40");
                }
                else if (pw.equals("40")) {
                    enteredpw.setText("50");
                }
                else if (pw.equals("50")) {
                    enteredpw.setText("60");
                }


                selectSubmitAction(getContext(),submitBtn);

                }
        });


//        final Button send = (Button) root.findViewById(R.id.submitButton);
//        send.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                try {
//                    GMailSender sender = new GMailSender("username@gmail.com", "password");
//                    sender.sendMail("This is Subject",
//                            "This is Body",
//                            "user@gmail.com",
//                            "user@yahoo.com");
//                } catch (Exception e) {
//                    Log.e("SendMail", e.getMessage(), e);
//                }
//            }
//        });

        // Code for the chip group
//        ChipGroup chipGroup = (ChipGroup)root.findViewById(R.id.chip_group);
//
//        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(ChipGroup group, @IdRes int checkedId) {
//                // Handle the chip group action.
//            }
//        });

        // Code to call telephone number
//        root.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0682289117", null)));
//            }
//        });


          // Code to setup the dropdown and based on selection set another dropdown
//        Spinner dropdown = root.findViewById(R.id.concerning);
//        String[] items = new String[]{"IT Application", "(IT) Hardware", "Employee","Process","Business / Client","External","Other"};
//        ArrayAdapter<String>adapter = new ArrayAdapter<>(null,
//                android.R.layout.simple_spinner_item,items);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dropdown.setAdapter(adapter);
//        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                Log.v("item", (String) parent.getItemAtPosition(position));
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });

        // Code for viewing through model class
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//       });
        return root;
    }


    public void selectSubmitAction(final Context context, Button submitBtn) {
        final CharSequence[] options = {"Submit and Share", "Initiate Payment", "Cancel"};

//        submitBtn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Submit Action")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                Log.d("AlertDialog", "Positive");
                                if (options[item].equals("Submit and Share")) {
                                    Toast.makeText(getContext(), "Submitted. Thanks!", Toast.LENGTH_SHORT).show();

                                    Log.i("Send email", "");
                                    String[] TO = {"dhaneshagrwal@gmail.com"};
                                    String[] CC = {"isreddy89@gmail.com"};
                                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                                    emailIntent.setData(Uri.parse("mailto:"));
                                    emailIntent.setType("text/plain");
                                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
                                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
                                    try {
                                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                                        //                     finish();
                                        //                     Log.i("Finished sending email...", "");
                                    } catch (ActivityNotFoundException ex) {
                                        Toast.makeText(getContext(),
                                                "There is no email client installed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                if (options[item].equals("Initiate Payment")) {
                                    Toast.makeText(getContext(), "Initiating Payments...", Toast.LENGTH_SHORT).show();
                                }
                                else if (options[item].equals("Cancel")) {
                                    dialog.dismiss();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("AlertDialog", "Negative");
                            }
                        })
                        .show();
//            }
//        });

    }

    public void selectImage(final Context context, Button imgBtn) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        imgBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle( "Choose your profile picture" )
                        .setItems(options, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                Log.d( "AlertDialog", "Positive" );
                                if (options[item].equals("Take Photo")) {
                                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(takePicture, 0);

                                } else if (options[item].equals("Choose from Gallery")) {
                                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(pickPhoto , 1);

                                } else if (options[item].equals("Cancel")) {
                                    dialog.dismiss();
                                }
                            }
                        })
                        .setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d( "AlertDialog", "Negative" );
                            }
                        } )
                        .show();
            }
        });


//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Choose your profile picture");
//
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//
//                if (options[item].equals("Take Photo")) {
//                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(takePicture, 0);
//
//                } else if (options[item].equals("Choose from Gallery")) {
//                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(pickPhoto , 1);
//
//                } else if (options[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContext().getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();

                            }
                        }

                    }
                    break;
            }
        }
    }

}
