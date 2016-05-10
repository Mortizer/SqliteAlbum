package com.aprendeandroid.sqlitealbum;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;

//import com.aprendeandroid.sqlitealbum.EditNameDialog.EditNameDialogListener;

//Al no tener soporte con versiones anteriores se usa Activity
public class SqlActivity extends Activity implements CustomListFragment.ListItemSelectedListener {
//		EditNameDialogListener, ListItemSelectedListener{

	private static String mainFragTag = "MAIN_FRAG";
    private static String editTagFragment = "EDIT_FRAG";
	private static String newTagFragment = "NEW_FRAG";

	private static int editFrag = 1;
	private static int newFrag = 2;

	private DBListFragment listFrag;
	private boolean isTwoPane = true;
	private int opcionFrag = 0;
	private int anotherFrag = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sql);

		if (!getResources().getBoolean(R.bool.twoPane)){
			isTwoPane = true;
		}

		Log.i("Main - ", "Paso 1");
		createListFragment();				
	}

	private void createListFragment() {

		Log.i("Main - ", "Paso 2");

		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

//		Se busca si existe el fragmente creado, mainFragTag es el TAG
//		con el que se va a crear el fragment.
		listFrag = (DBListFragment) fm.findFragmentByTag(mainFragTag);


//		No se ha creado el Fragment
		if (listFrag == null){
            Log.i("Main - ", "Paso 5");
			listFrag = new DBListFragment();

			Bundle parametros = new Bundle();
			parametros.putInt("listLayoutId", R.layout.list_fragment);
			parametros.putInt("emptyViewId", android.R.id.empty);

			listFrag.setArguments(parametros);

			ft.add(R.id.listPlace,listFrag,mainFragTag);
		}else{
//			Si se ha creado el Fragment
			ft.replace(R.id.listPlace,listFrag);
			ft.addToBackStack(null);
		}


        Log.i("Main - ", "Paso 6");
		ft.commit();
	}

//	------------------ ACTIONBAR ---------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.activity_menu, menu);
	    
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_add:
                Log.i("Paso - ", "Item selection " +  item.toString());
	        	//listFrag.addHoja();
				opcionFrag = newFrag;
				ConsultaEditNew(0,opcionFrag);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.context_menu, menu);
	}	

	public boolean onContextItemSelected(MenuItem item) {
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    switch (item.getItemId()) {
	        case R.id.menu_delete:
	        	listFrag.deleteHoja(info.position);
	            return true;
	        default:
	            return super.onContextItemSelected(item);
	    }
	}	

//	@Override
//	public void onFinishEditDialog(boolean result, String editedName) {
//		// Save the new hoja to the database
//		listFrag.onFinishEditDialog(result, editedName);
//	}
//
//	@Override
//	public String getNameToEdit() {
//		return listFrag.getNameToEdit();
//	}

//----- Declaración de la interface de CustomListFragment
	@Override
	public void onListItemSelected(int index, String tag, long id) {
		// TODO Auto-generated method stub
        Log.i("Paso ","interface");
		opcionFrag = editFrag;
        ConsultaEditNew(index,opcionFrag);
    }

//	Llamada de los distintos fragments para consultar/modificar y dar de alta un nuevo registro.
    private void ConsultaEditNew(int index,int opcion){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

			ft.setTransitionStyle(android.R.style.Widget_FragmentBreadCrumbs);
//				(android.R.anim.fade_in,android.R.anim.fade_out);

        Fragment editfr;

		Log.i("Interface", "Opcion seleccionada:" + String.valueOf(opcion));

		switch (opcion){
//			Fragment para editar y consultar un miembro de la lista DB.
			case 1:
				editfr = fm.findFragmentByTag(editTagFragment);

				if (editfr==null){
					editfr = new EditNameFragment();

				    if(anotherFrag < 0) {
						Bundle parametros = new Bundle();
						parametros.putInt("index", index);

						ft.add(R.id.detailDB, editfr, editTagFragment);
						ft.addToBackStack(null);
						anotherFrag = 1;
					}else{
						ft.replace(R.id.detailDB,editfr);
						ft.addToBackStack(null);
					}
				}else{

					ft.replace(R.id.detailDB,editfr);
					ft.addToBackStack(null);
				}
				break;
			case 2:
//				Fragment para dar de alta un nuevo registro en la tabla.
				editfr = fm.findFragmentByTag(newTagFragment);

				if (editfr==null){
					editfr = new NewNameFragment();
					if (anotherFrag < 0) {
						Bundle parametros = new Bundle();
						parametros.putInt("index", index);

						ft.add(R.id.detailDB, editfr, newTagFragment);
						ft.addToBackStack(null);
						anotherFrag = 1;
					}else{
						ft.replace(R.id.detailDB,editfr);
						ft.addToBackStack(null);
					}
				}else{
					ft.replace(R.id.detailDB,editfr);
					ft.addToBackStack(null);
				}
				break;
			default:
				break;
		}





        ft.commit();
    }

}
