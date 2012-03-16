/**
 * This file is part of Todo.txt Touch, an Android app for managing your todo.txt file (http://todotxt.com).
 *
 * Copyright (c) 2009-2012 Todo.txt contributors (http://todotxt.com)
 *
 * LICENSE:
 *
 * Todo.txt Touch is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any
 * later version.
 *
 * Todo.txt Touch is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with Todo.txt Touch.  If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * @author Todo.txt contributors <todotxt@yahoogroups.com>
 * @license http://www.gnu.org/licenses/gpl.html
 * @copyright 2009-2012 Todo.txt contributors (http://todotxt.com)
 */
package com.todotxt.todotxttouch;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Filter extends Activity {

	private final static String TAG = Filter.class.getSimpleName();
	private static ArrayList<String> appliedFilters = new ArrayList<String>();
	Intent data;
	ArrayList<String> priosArr;
	ArrayList<String> projectsArr;
	ArrayList<String> contextsArr;
	ArrayList<String> priosArrSelected;
	ArrayList<String> projectsArrSelected;
	ArrayList<String> contextsArrSelected;
	String dueDate;
	String searchTerm;
	TextView filterSelectionText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filter);

		data = getIntent();

		priosArr = data.getStringArrayListExtra(Constants.EXTRA_PRIORITIES);
		projectsArr = data.getStringArrayListExtra(Constants.EXTRA_PROJECTS);
		contextsArr = data.getStringArrayListExtra(Constants.EXTRA_CONTEXTS);
		priosArrSelected = data
				.getStringArrayListExtra(Constants.EXTRA_PRIORITIES_SELECTED);
		projectsArrSelected = data
				.getStringArrayListExtra(Constants.EXTRA_PROJECTS_SELECTED);
		contextsArrSelected = data
				.getStringArrayListExtra(Constants.EXTRA_CONTEXTS_SELECTED);
		dueDate = data.getStringExtra(Constants.EXTRA_DUEDATE);
		searchTerm = data.getStringExtra(Constants.EXTRA_SEARCH);
		filterSelectionText = (TextView) findViewById(R.id.filterSelectionText);
		updateStatus();

		/*
		 * Intent data = getIntent(); ArrayList<String> priosArr = data
		 * .getStringArrayListExtra(Constants.EXTRA_PRIORITIES);
		 * ArrayList<String> projectsArr = data
		 * .getStringArrayListExtra(Constants.EXTRA_PROJECTS); ArrayList<String>
		 * contextsArr = data
		 * .getStringArrayListExtra(Constants.EXTRA_CONTEXTS);
		 * 
		 * ArrayList<String> priosArrSelected = data
		 * .getStringArrayListExtra(Constants.EXTRA_PRIORITIES_SELECTED);
		 * ArrayList<String> projectsArrSelected = data
		 * .getStringArrayListExtra(Constants.EXTRA_PROJECTS_SELECTED);
		 * ArrayList<String> contextsArrSelected = data
		 * .getStringArrayListExtra(Constants.EXTRA_CONTEXTS_SELECTED); String
		 * dueDate = data.getStringExtra(Constants.EXTRA_DUEDATE); String
		 * searchTerm = data.getStringExtra(Constants.EXTRA_SEARCH);
		 */

		// Vars of data for alert boxes
		final CharSequence[] priorityCharArr = priosArr
				.toArray(new CharSequence[priosArr.size()]);
		final CharSequence[] projextCharArr = projectsArr
				.toArray(new CharSequence[projectsArr.size()]);
		final CharSequence[] contextCharArr = contextsArr
				.toArray(new CharSequence[contextsArr.size()]);

		Button priority = (Button) findViewById(R.id.addPriorityBtn);
		priority.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e(TAG, "Clicked Priority button");
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Filter.this);
				builder.setTitle("Add Priorities")
						.setCancelable(true)
						.setSingleChoiceItems(priorityCharArr, -1,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										if (which > -1) {
											Log.e(TAG, "Added Priority");
											Toast.makeText(
													getApplicationContext(),
													"Priority : "
															+ priorityCharArr[which],
													Toast.LENGTH_SHORT).show();
											priosArrSelected
													.add(priorityCharArr[which]
															.toString());
											appliedFilters.add("pri-"+priorityCharArr[which]);
											updateStatus();
										}
										dialog.dismiss();

									}
								});
				AlertDialog alert = builder.create();
				alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						Log.e(TAG, "Saved Project data(Fake)");
					}
				});
				alert.show();

			}
		});
		Button project = (Button) findViewById(R.id.addProjectBtn);
		project.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e(TAG, "Clicked Project button");
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Filter.this);
				builder.setTitle("Add Projects")
						.setCancelable(true)
						.setSingleChoiceItems(projextCharArr, -1,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										if (which > -1) {
											Log.e(TAG, "Added Priority");
											Toast.makeText(
													getApplicationContext(),
													"Project : "
															+ projextCharArr[which],
													Toast.LENGTH_SHORT).show();
											projectsArrSelected
													.add(projextCharArr[which]
															.toString());
											appliedFilters.add("prj-"+projextCharArr[which]);
											updateStatus();
										}
										dialog.dismiss();

									}
								});

				AlertDialog alert = builder.create();
				alert.setOnDismissListener(new DialogInterface.OnDismissListener() {

					@Override
					public void onDismiss(DialogInterface dialog) {
						Log.e(TAG, "Saved Project data(Fake)");
					}
				});
				alert.show();

			}
		});
		Button context = (Button) findViewById(R.id.addContextBtn);
		context.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e(TAG, "Clicked Context button");
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Filter.this);
				builder.setTitle("Add Context")
						.setCancelable(true)
						.setSingleChoiceItems(contextCharArr, -1,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										if (which > -1) {
											Log.e(TAG, "Added Priority");
											Toast.makeText(
													getApplicationContext(),
													"Context : "
															+ contextCharArr[which],
													Toast.LENGTH_SHORT).show();
											contextsArrSelected
													.add(contextCharArr[which]
															.toString());
											appliedFilters.add("ctx-"+contextCharArr[which]);
											updateStatus();
										}
										dialog.dismiss();
									}
								});

				AlertDialog alert = builder.create();
				alert.setOnDismissListener(new DialogInterface.OnDismissListener() {

					@Override
					public void onDismiss(DialogInterface dialog) {
						Log.e(TAG, "Saved Context data(Fake)");
					}
				});
				alert.show();

			}
		});

		Button ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v(TAG, "onClick OK");
				Intent data = new Intent();
				data = createIntent();
				appliedFilters = new ArrayList<String>();
				setResult(Activity.RESULT_OK, data);
				// data.Log.v(TAG, "Clearing all filter types.");
				appliedFilters = new ArrayList<String>();
				finish();
			}
		});

		Button cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v(TAG, "onClick Cancel");
				setResult(Activity.RESULT_CANCELED);
				finish();
			}
		});

		Button clear = (Button) findViewById(R.id.clear);
		clear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.v(TAG, "onClick Clear");
				appliedFilters = new ArrayList<String>();
				priosArrSelected = new ArrayList<String>();
				projectsArrSelected = new ArrayList<String>();
				contextsArrSelected = new ArrayList<String>();
				filterSelectionText.setText("Nothing currently selected");
			}
		});
	}

	public Intent createIntent() {
		Intent data = new Intent();
		data.putStringArrayListExtra(Constants.EXTRA_PRIORITIES,
				priosArrSelected);
		data.putStringArrayListExtra(Constants.EXTRA_PROJECTS,
				projectsArrSelected);
		data.putStringArrayListExtra(Constants.EXTRA_CONTEXTS,
				contextsArrSelected);
		/*
		 * data.putExtra(Constants.EXTRA_SEARCH, search.getText() .toString());
		 * String date = Integer.toString(duedatePicker.getYear()) + "-" +
		 * Integer.toString(duedatePicker.getMonth() + 1) + "-" +
		 * Integer.toString(duedatePicker.getDayOfMonth());
		 * data.putExtra(Constants.EXTRA_DUEDATE, date);
		 */
		data.putStringArrayListExtra(Constants.EXTRA_APPLIED_FILTERS,
				appliedFilters);
		return data;
	}

	public void updateStatus() {
		String status = "";
		for (String s : priosArrSelected) {
			status += "Pri-" + s + ", ";
		}
		for (String s : projectsArrSelected) {
			status += "Project-" + s + ", ";
		}
		for (String s : contextsArrSelected) {
			status += "Context-" + s + ", ";
		}
		if (!status.equals("")) {
			status = status.substring(0, status.length() - 2);
			filterSelectionText.setText((CharSequence) status);
		} else if (status.equals("")) {
			filterSelectionText.setText("Nothing currently selected");
		}
	}

	/*
	 * public void method(){
	 * 
	 * @Override protected void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState); mTabHost = getTabHost();
	 * 
	 * Drawable actionBarBg = getResources().getDrawable(
	 * R.drawable.title_background);
	 * mTabHost.getTabWidget().setBackgroundDrawable(actionBarBg);
	 * 
	 * LayoutInflater.from(this).inflate(R.layout.filter,
	 * mTabHost.getTabContentView(), true);
	 * 
	 * mTabHost.addTab(mTabHost
	 * .newTabSpec(getString(R.string.filter_tab_priorities)) //
	 * .setIndicator(getString(R.string.filter_tab_priorities))
	 * .setIndicator(buildIndicator(R.string.filter_tab_priorities))
	 * .setContent(R.id.priorities)); mTabHost.addTab(mTabHost
	 * .newTabSpec(getString(R.string.filter_tab_projects))
	 * .setIndicator(buildIndicator(R.string.filter_tab_projects))
	 * .setContent(R.id.projects)); mTabHost.addTab(mTabHost
	 * .newTabSpec(getString(R.string.filter_tab_contexts))
	 * .setIndicator(buildIndicator(R.string.filter_tab_contexts))
	 * .setContent(R.id.contexts)); mTabHost.addTab(mTabHost
	 * .newTabSpec(getString(R.string.filter_tab_search))
	 * .setIndicator(buildIndicator(R.string.filter_tab_search))
	 * .setContent(R.id.search));
	 * 
	 * 
	 * 
	 * final ListView priorities = (ListView) findViewById(R.id.prioritieslv);
	 * priorities.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	 * priorities.setAdapter(new ArrayAdapter<String>(this,
	 * R.layout.simple_list_item_multiple_choice, priosArr));
	 * setSelected(priorities, priosArrSelected);
	 * 
	 * final ListView projects = (ListView) findViewById(R.id.projectslv);
	 * projects.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	 * projects.setAdapter(new ArrayAdapter<String>(this,
	 * R.layout.simple_list_item_multiple_choice, projectsArr));
	 * setSelected(projects, projectsArrSelected);
	 * 
	 * final ListView contexts = (ListView) findViewById(R.id.contextslv);
	 * contexts.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	 * contexts.setAdapter(new ArrayAdapter<String>(this,
	 * R.layout.simple_list_item_multiple_choice, contextsArr));
	 * setSelected(contexts, contextsArrSelected);
	 * 
	 * final EditText search = (EditText) findViewById(R.id.searchet);
	 * search.setText(searchTerm);
	 * 
	 * final DatePicker duedatePicker = (DatePicker)
	 * findViewById(R.id.dueDatePicker); if (dueDate != null) { dueDate =
	 * dueDate.substring(4); Date date = Date.valueOf(dueDate);
	 * duedatePicker.updateDate(date.getYear(), date.getMonth(), date.getDay());
	 * } else { Calendar c = Calendar.getInstance();
	 * duedatePicker.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
	 * c.get(Calendar.DAY_OF_MONTH)); }
	 * 
	 * Button ok = (Button) findViewById(R.id.ok); ok.setOnClickListener(new
	 * OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { Log.v(TAG, "onClick OK"); Intent
	 * data = new Intent(); Log.v(TAG, "Clearing all filter types.");
	 * appliedFilters = new ArrayList<String>();
	 * data.putStringArrayListExtra(Constants.EXTRA_PRIORITIES,
	 * getItems(priorities, "Priority"));
	 * data.putStringArrayListExtra(Constants.EXTRA_PROJECTS, getItems(projects,
	 * "Project")); data.putStringArrayListExtra(Constants.EXTRA_CONTEXTS,
	 * getItems(contexts, "Context")); data.putExtra(Constants.EXTRA_SEARCH,
	 * search.getText() .toString()); String date =
	 * Integer.toString(duedatePicker.getYear()) + "-" +
	 * Integer.toString(duedatePicker.getMonth()+1) + "-" +
	 * Integer.toString(duedatePicker.getDayOfMonth());
	 * data.putExtra(Constants.EXTRA_DUEDATE, date);
	 * data.putStringArrayListExtra(Constants.EXTRA_APPLIED_FILTERS,
	 * appliedFilters); setResult(Activity.RESULT_OK, data); finish(); } });
	 * 
	 * Button cancel = (Button) findViewById(R.id.cancel);
	 * cancel.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { Log.v(TAG, "onClick Cancel");
	 * setResult(Activity.RESULT_CANCELED); finish(); } });
	 * 
	 * Button clear = (Button) findViewById(R.id.clear);
	 * clear.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { Log.v(TAG, "onClick Clear");
	 * appliedFilters = new ArrayList<String>(); setSelected(priorities, null);
	 * setSelected(projects, null); setSelected(contexts, null);
	 * search.setText(""); } }); }
	 * 
	 * private View buildIndicator(int textRes) { final TextView indicator =
	 * (TextView) this.getLayoutInflater().inflate( R.layout.tab_indicator,
	 * mTabHost.getTabWidget(), false); indicator.setText(textRes); return
	 * indicator; }
	 * 
	 * private static ArrayList<String> getItems(ListView adapter, String type)
	 * { ArrayList<String> arr = new ArrayList<String>(); int size =
	 * adapter.getCount(); for (int i = 0; i < size; i++) { if
	 * (adapter.isItemChecked(i)) { arr.add((String)
	 * adapter.getAdapter().getItem(i)); Log.v(TAG, " Adding " + (String)
	 * adapter.getAdapter().getItem(i) + " to applied filters."); if
	 * (!appliedFilters.contains(type)) { appliedFilters.add(type); Log.v(TAG,
	 * " Adding " + type + " to applied filter types."); } } } return arr; }
	 * 
	 * private static void setSelected(ListView lv, ArrayList<String> selected)
	 * { int count = lv.getCount(); for (int i = 0; i < count; i++) { String str
	 * = (String) lv.getItemAtPosition(i); lv.setItemChecked(i, selected != null
	 * && selected.contains(str)); } } }
	 */
}
