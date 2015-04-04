package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import course.labs.todomanager.ToDoItem.Status;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// TODO - Create a View for the ToDoItem at specified position
    // Remember to check whether convertView holds an already allocated View
    // before created a new View.
    // Consider using the ViewHolder pattern to make scrolling more efficient
    // See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html

    //http://www.javacodegeeks.com/2013/09/android-viewholder-pattern-example.html
    static class ViewHolder{
        TextView title;
        CheckBox status;
        TextView priority;
        TextView  date;

        ViewHolder(View v){
            title = (TextView)v.findViewById(R.id.titleView);
            status = (CheckBox)v.findViewById(R.id.statusCheckBox);
            priority = (TextView)v.findViewById(R.id.priorityView);
            date = (TextView)v.findViewById(R.id.dateView);
        }
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            convertView = mInflater.inflate(R.layout.todo_item, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*
        * The convertView argument is essentially a "ScrapView" as described is Lucas post
        * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
        * It will have a non-null value when ListView is asking you recycle the row layout.
        * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
        */
        /*if(convertView==null){

            // TODO - Inflate the View for this ToDoItem
            // from todo_item.xml.
            // inflate the layout
            //LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            //convertView = inflater.inflate(R.layout.todo_item, parent, false);

            convertView = LayoutInflater.from(mContext).inflate(R.layout.todo_item, null);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.priority = (TextView) convertView.findViewById(R.id.priority);
            viewHolder.status = (CheckBox) convertView.findViewById(R.id.statusCheckBox);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);

            // store the holder with the view.
            convertView.setTag(viewHolder);

        }else{
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }
*/
        // TODO - Get the current ToDoItem
        final ToDoItem toDoItem = mItems.get(position);

        if(toDoItem != null){

            // TODO - Fill in specific ToDoItem data
            // Remember that the data that goes in this View
            // corresponds to the user interface elements defined
            // in the layout file
            // TODO - Display Title in TextView
            viewHolder.title.setText(toDoItem.getTitle());

            // TODO - Set up Status CheckBox
            /*
            final CheckBox statusView = null;
            // TODO - Must also set up an OnCheckedChangeListener,
            // which is called when the user toggles the status checkbox
            */

            viewHolder.status.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    Log.i(TAG,"Entered onCheckedChanged()");
                    toDoItem.setStatus( isChecked ? Status.DONE : Status.NOTDONE);
                }
            });

            viewHolder.status.setChecked(toDoItem.getStatus() == Status.DONE);

            // TODO - Display Priority in a TextView
            viewHolder.priority.setText(toDoItem.getPriority().toString());

            // TODO - Display Time and Date
            viewHolder.date.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));

        }

		// Return the View you just created
		return convertView;

	}
}
