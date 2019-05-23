package myhealth.ufscar.br.myhealth.tasks;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;


import myhealth.ufscar.br.myhealth.data.AccessToken;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.usecases.GetAccessData;

public class AccessCodeTask extends AsyncTask<String, Integer, AccessToken> {

    private AccessCodeTask.OnTaskInteraction mListener;

    public AccessCodeTask(Fragment fragment){
        if (fragment instanceof AccessCodeTask.OnTaskInteraction) {
            mListener = (AccessCodeTask.OnTaskInteraction) fragment;
        } else {
            throw new RuntimeException(fragment.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected AccessToken doInBackground(String... data) {
        try {
            return GetAccessData.execute(data[0]);
        } catch (NoConnectionException e) {
            Log.i("Access Token", "No Connection");
        }
        return null;
    }

    @Override
    protected void onPostExecute(AccessToken accessToken) {
        super.onPostExecute(accessToken);

        if (accessToken != null && mListener != null) {
            mListener.onTaskFinsh(accessToken.getToken());
        }
    }

    public interface OnTaskInteraction{
        void onTaskFinsh(String result);
    }
}
