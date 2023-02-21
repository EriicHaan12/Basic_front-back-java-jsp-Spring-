package weberichan;

class Horse extends Thread implements Comparable<Horse>{
	   private String name1;
	   int rank;
	   
	   public Horse(String name) {
	      this.name1 = name;
	   }
	   
	   public String getName1() {
	      return name1;
	   }

	   public void setName1(String name) {
	      this.name1 = name;
	   }

	   public int getRank() {
	      return rank;
	   }

	   public void setRank(int rank) {
	      this.rank = rank;
	   }


		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
				System.out.println("\n" + name1 + " : ");
				for (int j = 0; j < i; j++) {
					System.out.print("-");
				}
				System.out.print(">");

				for (int j = 49; j > i; j--) {
					System.out.print("-");
				}

				System.out.println();
				System.out.println();
				System.out.println();

				try {
					Thread.sleep((int) (Math.random() * 500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println(name1 + " 끝");

			setRank(RaceHorse.strRank);
			RaceHorse.strRank++;
		}

		@Override
		public int compareTo(Horse hor) {
			if (rank > hor.getRank()) {
				return 1;
			} else {
				return -1;
			}
		}
	}