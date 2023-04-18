import java.util.Arrays;

public class Garland
{
	Garland()
	{
	}

	Garland(String [] strings)
	{
		for (int i = 0; i < strings.length; i++)
			addString(strings[i]);
	}

	private Node head; // Reference to the top-left node in this garland.
	private Node tail; // Reference to the top-right node in this garland.
	private int size; // The number of strings currently in this garland.

	public static Node stringToLinkedList(String s)
	{
		// no-op if the string is invalid or is empty
		// using short circuiting
		if (s == null || s.length() == 0)
			return null;

		Node ans = new Node(s.charAt(0));
		Node temp = ans;

		for (int i = 1; i < s.length(); i++)
		{
			temp.down = new Node(s.charAt(i));
			temp = temp.down;
		}

		return ans;
	}

	public static String linkedListToString(Node head)
	{
		// Two temp nodes we will use to traverse the list
		Node temp1 = head;
		Node temp2 = temp1;

		// Tracker to count how many characters are in the string
		int charCount = 0;

		// Follow the nodes from the head down until the end 
		// of the string to get our needed length
		while (temp2 != null)
		{
			temp2 = temp2.down;
			charCount++;
		}

		// Create a new char array that is the size of the string
		char [] result = new char[charCount];

		// Iterate through our linked list and our char array
		// at the same time, copying the linked list data
		// into the char array
		for (int i = 0; i < charCount; i++)
		{
			result[i] = temp1.data;
			temp1 = temp1.down;
		}

		// Save the result to a string using the built-in 
		// String constructor
		String endResult = new String(result);
		return endResult;
	}

	public Node head()
	{
		return this.head;
	}

	public int size()
	{
		return this.size;
	}

	public void addString(String s)
	{
		if (s == null || s.length() == 0)
			return;

		// List is empty, so the head and tail gets updated
		if (head() == null)
		{
			this.head = stringToLinkedList(s);
			this.tail = this.head;
			size += 1;
		}

		// List already has a string in it
		// Inserting at the tail instead
		else
		{
			tail.next = stringToLinkedList(s);
			this.tail = tail.next;
			this.size += 1;
		}
	}

	public Node getNode(int index)
	{
		// Temporary node that will return the reference we need
		Node ans = head;

		// check if the index is out of bounds for the size - 1 or negative
		if (index > this.size - 1 || index < 0)
			return null;

		// loop through the top row from left to right 
		// until we reach the requested index node
		for (int i = 0; i < index; i++)
		{
			ans = ans.next;
		}

		return ans;
	}

	public String getString(int index)
	{
		if (getNode(index) == null)
			return null;

		// Using DRY to traverse the list and
		// get the head node of the string we want
		Node temp1 = getNode(index);
		return linkedListToString(temp1);
	}

	public boolean removeString(int index)
	{
		if (index > this.size - 1 || index < 0)
			return false;

		// If we are removing the head, we need to
		// update the head to the next linked list
		if (index == 0)
		{
			this.head = this.head.next;
			size -= 1;
			return true;
		}

		// If we are removing the tail, we need to
		// update the tail to the previous linked list
		// which means we will have to loop through the list
		if (index == size - 1)
		{
			Node temp = getNode(size - 2);
			this.tail = temp;
			tail.next = null;
			size -= 1;
			return true;
		}

		// if the node is in the middle of the list, we will loop to the previous node
		// and set the next reference to the node in front of it which will
		// allow java to use garbage collection on the requested index node
		else
		{
			Node ans = getNode(index - 1);
			ans.next = ans.next.next;
			size -= 1;
			return true;
		}
	}

	public boolean printString(int index)
	{
		if (index > this.size - 1 || index < 0)
		{
			System.out.println("(invalid index)");
			return false;
		}

		// call getString in order to use DRY and get our string
		String result = getString(index);
		System.out.println(result);
		return true;
	}

	public void printStrings()
	{
		if (this.size == 0)
		{
			System.out.println("(empty list)");
			return;
		}

		// two temp nodes we will use to navigate through the garland
		Node temp1 = this.head;
		Node temp2 = temp1;

		// Use temp2 to go downwards on the garland, then use temp1 to
		// go to the right once we hit the bottom of the current string
		while (temp2 != null)
		{
			System.out.print(temp2.data);
			temp2 = temp2.down;

			if (temp2 == null)
			{
				System.out.println();
				if (temp1.next != null)
				{
					temp1 = temp1.next;
					temp2 = temp1;
				}

				else
					return;
			}
		}
	}

	public static double difficultyRating()
	{
		double difficulty = 2.8;
		return difficulty;
	}

	public static double hoursSpent()
	{
		double hours = 9.6;
		return hours;
	}
}