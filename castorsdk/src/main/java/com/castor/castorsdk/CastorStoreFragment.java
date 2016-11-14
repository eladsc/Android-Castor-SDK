    package com.castor.castorsdk;

    import android.app.Fragment;
    import android.content.Context;
    import android.net.Uri;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.webkit.WebResourceRequest;
    import android.webkit.WebView;
    import android.webkit.WebViewClient;



    /**
     * Fragment that displays the store UI for the particular Product you have initialise it with. The class, when displied to the user will take care of the sale and call the delegate method when it is done.
     * {@link CastorStoreFragment.CastorStoreFragmentListener} interface
     * to handle interaction events.
     * Use the {@link CastorStoreFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public class CastorStoreFragment extends Fragment {
    //constatnts
        private static final String ARG_PRODUCT = "product";



        private CastorStoreFragmentListener  mListener;

    private Product mProduct;
        private WebView mWebview;

        public CastorStoreFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param product The product that should be offered for sale in the fragment.
         * @return A new instance of fragment CastorStoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static CastorStoreFragment newInstance(Product product) {
            CastorStoreFragment fragment = new CastorStoreFragment();
            Bundle args = new Bundle();
            args.putParcelable(ARG_PRODUCT, product);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mProduct = getArguments().getParcelable(ARG_PRODUCT);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {



    View view = inflater.inflate(R.layout.fragment_castor_store, container, false);        // Inflate the layout for this fragment
            mWebview = (WebView) view.findViewById(R.id.webView);
            mWebview.getSettings().setJavaScriptEnabled(true);

            mWebview.loadUrl(mProduct.getUrl());
            mWebview.setWebViewClient(new WebViewClient() {


                @Override
                public boolean shouldOverrideUrlLoading(WebView view,  String url) {
                    // Here put your code
                    Log.d("My Webview", url);
    if ("3dcastor.com/completepurchase".contains(url)){
    if (mListener != null) mListener.complete(CompletionStatus.SUCCESS);
    return true;
    }
                    // return true; //Indicates WebView to NOT load the url;
                    return false; //Allow WebView to load url
                }
            });
            return view;
        }



        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof CastorStoreFragmentListener) {
                mListener = (CastorStoreFragmentListener) context;



            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }

        @Override
        public void onDetach() {
            super.onDetach();
            mListener = null;
        }

        /**
         * This interface must be implemented by activities that contain this
         * fragment to allow an interaction in this fragment to be communicated
         * to the activity and potentially other fragments contained in that
         * activity.
         */
        public interface CastorStoreFragmentListener {
            // you can define any parameter as per your requirement
            public void complete(CompletionStatus result);
        }



    }
