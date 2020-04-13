package com.rom.works.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.rom.works.MainActivity;
import com.rom.works.helper.DefinitionBroadcastReceiver;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class VocabularNotificationService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Intent service = new Intent(getApplicationContext(), MainActivity.class);
        getApplicationContext().startService(service);
        DefinitionBroadcastReceiver.scheduleJob(getApplicationContext()); // reschedule the job
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
