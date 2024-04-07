package com.example.konnect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdateReportActivity extends AppCompatActivity {

    private ListView reportsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatereport);

        reportsListView = findViewById(R.id.reports_list_view);

        String userId = User.getInstance().getID(); // Get the ID of the user

        // URL of your server
        String url = "http://coms-309-001.class.las.iastate.edu:8080/reports" + userId;

        // Create a new JsonArrayRequest
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // The response is a JSON array of reports
                        // Parse this JSON array and display the reports in the ListView
                        List<String> reports = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject report = response.getJSONObject(i);
                                reports.add(report.getString("report")); // Assuming "report" is the key for the report content
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // Display the reports in the ListView
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(UpdateReportActivity.this,
                                android.R.layout.simple_list_item_1, reports);
                        reportsListView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the error
            }
        });

        // Add the request to the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(UpdateReportActivity.this);
        queue.add(jsonArrayRequest);

        reportsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When a report is clicked, start the ReportDetailActivity
                Intent intent = new Intent(UpdateReportActivity.this, ReportDetailActivity.class);
                intent.putExtra("reportId", position); // Pass the report ID to the ReportDetailActivity
                startActivity(intent);
            }
        });
    }
}
