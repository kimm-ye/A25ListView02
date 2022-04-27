package com.kosmo.a25listview02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KOSMO123";

    //2가지 형태의 데이터로 사용할 배열 생성
    String[] idolGroup = {"엑소", "방탄", "워너원","뉴이스트", "갓세븐", "비투비", "빅스"};
    int[] teamCount = {9,7,11,5,7,7,6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, idolGroup);
        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(adapter);*/

        //어댑터 객체를 별도의 클래스를 통해 만들어줌.
        //개발자가 정의한 어댑터 객체 생성
        final MyAdapter adapter = new MyAdapter();
        //리스트뷰 위젯을 가져옴
        ListView listView1 = findViewById(R.id.listView1);
        //리스트 뷰에 어댑터를 설정한다.
        listView1.setAdapter(adapter);

        //리스트뷰에 리스너를 부착한다.
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "선택한 index:"+position);
                //그룹명과 팀 인원수까지 토스트로 출력한다.
                Toast.makeText(getApplicationContext(), idolGroup[position]+ "("+ teamCount[position] +") 선택됨",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //메서드 안에 메서드는 불가능하나, 클래스 안에 클래스 정의는 가능하다. 이것을 내부 클래스라고 한다.
    //내부클래스 형태로 클래스 정의
    /*
    개발자 정의 커스텀 어댑터 클래스 선언
        : BaseAdapter를 상속받아 선언한다.
        그리고 관련 4개 메서드를 오버라이딩해야한다
     */
    class MyAdapter extends BaseAdapter{

        //어댑터 객체가 가진 항목의 갯수를 반환한다.
        @Override
        public int getCount() {
            return idolGroup.length;
        }

        //어댑터가 가진 하나의 항목을 반환한다.
        @Override
        public Object getItem(int position) {
            return idolGroup[position];
        }

        //해당 항목의 인덱스를 반환한다.
        @Override
        public long getItemId(int position) {
            return position;
        }

        //특정 뷰 하나를 반환한다.
        //어댑터가 가진 뷰 중에 하나를 반환한다.
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //Java코드를통해 레이아웃 및 텍스트뷰 생성
            //첫번째 텍스트뷰 생성 : 아이돌 그룹명을 표시한다.
            TextView view1 = new TextView(getApplicationContext());
            view1.setText(idolGroup[position]); //텍스트 입력 (아이돌 그룹이 String이라서 바로씀)
            view1.setTextSize(40.0f); //글자 크기
            view1.setTextColor(Color.BLUE); //글자 색깔

            //두번째 텍스트뷰 생성 : 팀 인원수를 표시한다.
            TextView view2 = new TextView(getApplicationContext());
            //Boxing처리 후 삽입
            view2.setText(new Integer(teamCount[position]).toString()); //숫자를 String으로 객체화

            /*
            TextView에는 기본자료형으로 데이터를 삽입할 수 없다. 따라서 반드시 Integer클래스와
            같은 Wrapper클래스를 통해 Boxing처리한 후 삽입해야 한다. 아래와 같이 기본자료형(int형)
            으로 삽입하면 런타임 에러가 발생하여 앱이 실행되지 않는다.
             */
            //view2.setText(teamCount[position]); ->에러발생 setText에는 기본자료형을 쓸 수없어서 rapper클래스로 박싱처리해줘야한다.

            view2.setTextSize(40.0f);
            view2.setTextColor(Color.RED);

            //선형(리니어)레이아웃을 생성한 후 텍스트 뷰를 추가한다.
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            layout.addView(view1);
            layout.addView(view2);

            return layout;
        }
    }
}