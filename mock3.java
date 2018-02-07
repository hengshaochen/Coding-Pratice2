Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

[1,2],[3,10] [12,16]

[1,3][6,9]  [2,5] → [1,5] [6,9]

Sort by start point   [2,5]
[1,3] [2,5] [6,9]  → [1,5] [6,9]
   i      j  [1, Math.max( interval(i).end , interval(j).end) → [1,5]

Find the insert point → a. Sort by start point, b. Insert
Merge 
 0       1
Class Interval {
	int start, end;
}



Input = { [1,3] [6,9] },   newInteval = [2,5]

Public void insert_and_merge(List<Interval> input, Interval newInterval) {
	If (input == null || input.size() == 0) {
		Return newInterval;
}
If (newInterval == null) {
	Return input;
}

Comparator<Interval> cmp = new Comparator<Interval>() {
	Public int compare(Interval e1, Interval e2) {
		Return e1.start - e2.start;
}
}

Collections.sort(input, cmp);

// Insert
For (int i = 0; i < input.size(); i++) {
	if ( input.get(i).start >= newInterval.start) {
		input.add(i, newInterval);
		break;
	}
}


	// [1,3][6,9] [10,13] 
	If (input.get(input.size() - 1).start < newInterval.start) {
		input.add(newInterval);
}

// Merge
	[1,3] [2,5] [6,9]   pre.end = 3   cur.start = 2
	Pre   cur
             pre   cur
	[1,5]  Math.max(3,5)
		Reutrn merge(input);
}

Public List<Interval> merge(List<Interval> input) {
	List<Inteval> ans = new ArrayList<>();
	Interval pre = input.get(0);
	For ( int i = 1; i < input.size(); i++) {
		Interval cur = input.get(i);
		If (pre.end >= cur.start) {
			// merge
			Pre.end = Math.max(pre.end, cur.end);
		} else {
			Pre = cur;
			ans.add(pre);
}
}
ans.add(pre);
	Return ans;
}
}




