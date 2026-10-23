package com.example.practicalfive;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    private TextView textViewMunicipalityName;
    private ImageView imageViewMunicipality;
    private TextView textViewInformation;
    private Button buttonVisitSite;

    private String municipalityName;
    private String officialWebsiteUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        textViewMunicipalityName = findViewById(R.id.textViewMunicipalityName);
        imageViewMunicipality = findViewById(R.id.imageViewMunicipality);
        textViewInformation = findViewById(R.id.textViewInformation);
        buttonVisitSite = findViewById(R.id.buttonVisitSite);

        String passedName = getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY);

        TextProcessor textProcessor = new TextProcessor();
        municipalityName = textProcessor.processMunicipality(passedName);

        if (municipalityName == null) {
            Toast.makeText(
                    this,
                    "Municipality information is unavailable.",
                    Toast.LENGTH_SHORT
            ).show();
            finish();
            return;
        }

        customiseMunicipalityScreen();
        setupVisitSiteButton();
    }

    private void customiseMunicipalityScreen() {
        int municipalityColourResource;
        int municipalityImageResource;
        int municipalitySummaryIndex;

        switch (municipalityName) {
            case "Beijing":
                municipalityColourResource = R.color.municipality_beijing;
                municipalityImageResource = R.drawable.beijing;
                municipalitySummaryIndex = 0;
                officialWebsiteUrl = "https://english.beijing.gov.cn/";
                break;

            case "Shanghai":
                municipalityColourResource = R.color.municipality_shanghai;
                municipalityImageResource = R.drawable.shanghai;
                municipalitySummaryIndex = 1;
                officialWebsiteUrl = "https://english.shanghai.gov.cn/";
                break;

            case "Chongqing":
                municipalityColourResource = R.color.municipality_chongqing;
                municipalityImageResource = R.drawable.chongqing;
                municipalitySummaryIndex = 2;
                officialWebsiteUrl = "https://english.cq.gov.cn/";
                break;

            case "Tianjin":
                municipalityColourResource = R.color.municipality_tianjin;
                municipalityImageResource = R.drawable.tianjin;
                municipalitySummaryIndex = 3;
                officialWebsiteUrl = "https://en.tj.gov.cn/";
                break;

            default:
                Toast.makeText(
                        this,
                        "Municipality information is unavailable.",
                        Toast.LENGTH_SHORT
                ).show();
                finish();
                return;
        }

        int municipalityColour = ContextCompat.getColor(
                this,
                municipalityColourResource
        );

        String[] municipalitySummaries = getResources().getStringArray(
                R.array.municipalities_array
        );

        textViewMunicipalityName.setText(municipalityName);
        textViewMunicipalityName.setBackgroundColor(municipalityColour);
        textViewMunicipalityName.setTextColor(ContextCompat.getColor(
                this,
                R.color.color_text_light
        ));

        imageViewMunicipality.setImageResource(municipalityImageResource);

        textViewInformation.setText(municipalitySummaries[municipalitySummaryIndex]);
        textViewInformation.setTextColor(municipalityColour);

        buttonVisitSite.setBackgroundTintList(
                ColorStateList.valueOf(municipalityColour)
        );
        buttonVisitSite.setTextColor(ContextCompat.getColor(
                this,
                R.color.color_text_light
        ));
    }

    private void setupVisitSiteButton() {
        buttonVisitSite.setOnClickListener(view -> {
            Intent websiteIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(officialWebsiteUrl)
            );

            try {
                startActivity(websiteIntent);
            } catch (ActivityNotFoundException exception) {
                Toast.makeText(
                        this,
                        "No application can open this website.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}