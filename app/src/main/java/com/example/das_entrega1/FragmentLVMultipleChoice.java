package com.example.das_entrega1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentLVMultipleChoice extends Fragment {


    ListView lv;
    String[] datos={"Pizza 1","Pizza 2","Pizza 3","Pizza 4","Pizza 5"};
    int[] comida={R.drawable.arrozz,R.drawable.ensalada,R.drawable.esp,R.drawable.las,R.drawable.pizza};
    //String[] ingredientes={"Tomate,aguacate...","rucula,..."};
    //double[] precios = {12.7,....};
    int[] titulos = {23,54,65,8,10};

    public interface listenerDelFragment{
        //void seleccionarElemento(String equipo, int imagen, int titulos);
        void ponerLista();
        //ArrayList<String> seleccionados();
    }
    private listenerDelFragment elListener;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragmentlvmultiplechoice,container,false);
        lv=v.findViewById(R.id.lv);

        return v;
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        elListener.ponerLista();


        /*

        //PARA HACER CON CHECKBOX
        ArrayAdapter eladaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice,datos);
        //ArrayAdapter eladaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,datos);
        lv.setAdapter(eladaptador);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                elListener.seleccionarElemento(datos[position],comida[position],titulos[position]);
                System.out.println(datos[position]);
            }
        });

         */

    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            elListener=(listenerDelFragment) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException("La clase " +context.toString()
                    + "debe implementar listenerDelFragment");
        }
    }
}