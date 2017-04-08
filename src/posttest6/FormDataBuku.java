/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posttest6;

/**
 *
 * @author USER
 */
import java.sql.*;
import javax.swing.table.DefaultTableModel;
public class FormDataBuku extends javax.swing.JFrame {
private DefaultTableModel model; 
private Connection con = koneksi.getConnection();
private Statement stt;
private ResultSet rss;
int id;

/**
     * Creates new form FormDataBuku
     */
    public FormDataBuku() {
        initComponents();
        
    }
    private void InitTable(){
        model = new DefaultTableModel();
        model.addColumn("Title");   //Menambah kolom yg namanya Title/judul
        model.addColumn("Author"); //Menambah kolom yg namanya Author/penulis
        model.addColumn("Price"); //Menambah kolom yg namanya Price/harga
        model.addColumn("Id");   //Menambah kolom yg namanya Id
        tabeltampil.setModel(model);
        tabeltampil.getColumn("Id").setPreferredWidth(0);
        tabeltampil.getColumn("Id").setMinWidth(0);
        tabeltampil.getColumn("Id").setWidth(0);
        tabeltampil.getColumn("Id").setMaxWidth(0);
        //Kolom Id digunakan untuk update atau delete pada data yg diinginkan
    }
    
    private void TampilData(){ // fungsi yg namanya TampilData digunakan untuk menampilkan data dari Database tabel yg namanya book
        try{
            String sql = "SELECT * FROM book"; //Query sql untuk menampilkan data dari tabel yg namanya book dari database kita
            stt = con.createStatement();
            rss = stt.executeQuery(sql); 
            while(rss.next()){ // perulangan resultset dari variable rss 
                //menampilkan data sesuai array :
                Object[] o = new Object[4];
                o[0] = rss.getString("title");    //Data kolom title dari tabel book akan dimasukkan ke object o
                o[1] = rss.getString("author");   //Data kolom author dari tabel book dimasukkan ke object o
                o[2] = rss.getInt("price");       //Data kolom price dari tabel book dimasukkan ke object o
                o[3] = rss.getInt("id");         //Data kolom id dari tabel book dimasukkan ke object o
                model.addRow(o);                 // Data-data tersebut kemudian di masukkan ke dalam tempat penampung yaitu bernama tabeltampil
                
            }
        }catch(SQLException e){
            System.out.println(e.getMessage()); // Exception disni digunakan untuk menangani jika terjadi kesalahan pada inputan
        }
        
    }
    
    private void TambahData(){ //Fungsi yg namanya TambahData digunakan untuk menambah data ke Database tabel yg namanya book
        String title = tftitle.getText(); //Mengambil data title dari tftitle bertipe string/karakter
        String author = cbauthor.getSelectedItem().toString(); //Mengambil data author dari cbauthor bertipe string/karakter
        int price = Integer.parseInt(tfprice.getText()); //Mengambil data price  dari tfprice bertipe integer/bilanganbulat
        try{
            String sql = "INSERT INTO book VALUES(NULL,'"+title+"','"+author+"',"+price+")"; 
            //Query SQL yg digunakan untuk menginputkan data yaitu Insert Into jadi diinputkan delam tabel yang bernama book
            stt = con.createStatement();
            stt.executeUpdate(sql); //executeUpdate adalah yang menjalankan querynya
            model.addRow(new Object[]{title,author,price});
        }catch(SQLException e){
            System.out.println(e.getMessage()); // Exception disni digunakan untuk menangani jika terjadi kesalahan pada inputan
        }
    }
    
    private void UpdateData(){ //Fungsi yg namanya UpdateData digunakan untuk mengubah data atau mengupdate data pada Database tabel yg namanya book
        String title = tftitle.getText(); //Mengambil data title dari tftitle bertipe string/karakter
        String author = cbauthor.getSelectedItem().toString();  //Mengambil data author dari cbauthor bertipe string/karakter
        int price = Integer.parseInt(tfprice.getText()); //Mengambil data price  dari tfprice bertipe integer/bilanganbulat
        int ids = Integer.parseInt(idd.getText()); //Mengambil data id dari jLabel idd
        try{
            String sql = "update buku set title='"+title+"', author='"+author+"', price='"+price+"'Where id='"+ids+"'";
            //Query sql yg digunakan untuk mengupdate data dimana id = ids, ids berisi id title,author, dan price
            stt = con.createStatement();
            stt.executeUpdate(sql);  //executeUpdate adalah yang menjalankan querynya
        }catch(SQLException e){
            System.out.println(e.getMessage()); // Exception disni digunakan untuk menangani jika terjadi kesalahan pada inputan
        }
    }
    
    private void HapusData(){ //Fungsi yg namanya HapusData digunakan untuk menghapus data dari Database tabel yg namanya book
        String title = tftitle.getText(); //Mengambil data title dari tftitle bertipe string/karakter
        String author = cbauthor.getSelectedItem().toString(); //Mengambil data author dari cbauthor bertipe string/karakter
        int price = Integer.parseInt(tfprice.getText()); //Mengambil data price  dari tfprice bertipe integer/bilanganbulat
        int ids = Integer.parseInt(idd.getText()); //Mengambil data ID yang dari jLabel idd
        try{
            String sql = "delete from book where id='"+ids+"'";
             //Query sql yg digunakan untuk menghapus data dimana id = ids, ids berisi id title,author, dan price
            stt = con.createStatement();
            stt.executeUpdate(sql); //executeUpdate adalah yang menjalankan querynya
        }catch(SQLException e){
            System.out.println(e.getMessage()); // Exception disni digunakan untuk menangani jika terjadi kesalahan pada inputan
        }  
    }
    
    private void CariData(){ //Fungsi yg namanya CariData digunakkan untuk mencari data dari Database tabel yg namanya book
        String caridengan = cbby.getSelectedItem().toString();
        //String caridengan merupakan string yang memilih data pilihan dari cbby  yg bertipe string/karakter
        try{
            String sql = "select * from book where "+caridengan+" like '%"+tfsearch.getText()+"%'";
            //Query sql yg digunakan untuk  menampilkan data dimana kondisi variable caridengan = yang di tulis pada tfsearch
            stt = con.createStatement();
            rss = stt.executeQuery(sql); //executeUpdate adalah yang menjalankan querynya
            ResultSet rss=stt.executeQuery(sql);
            while(rss.next()){   // perulangan resultset dari variable rss 
                //menampilkan data sesuai array :
                Object[] o=new Object[4];
                o[0]=rss.getString("title");   //Data kolom title dari tabel book akan dimasukkan ke object o
                o[1]=rss.getString("author");  //Data kolom author dari tabel book dimasukkan ke object o
                o[2]=rss.getInt("price");      //Data kolom price dari tabel book dimasukkan ke object o
                o[3]=rss.getInt("id");         //Data kolom id dari tabel book dimasukkan ke object o
                model.addRow(o);               // Data-data tersebut kemudian di masukkan ke dalam tempat penampung yaitu bernama tabeltampil 
            }
            
        }catch(SQLException e){System.out.println(e.getMessage());} // Exception disni digunakan untuk menangani jika terjadi kesalahan pada inputan
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlbiru = new javax.swing.JPanel();
        pnlabu = new javax.swing.JPanel();
        lblform = new javax.swing.JLabel();
        pnlhijau = new javax.swing.JPanel();
        lbltitle = new javax.swing.JLabel();
        lblauthor = new javax.swing.JLabel();
        lblprice = new javax.swing.JLabel();
        tftitle = new javax.swing.JTextField();
        tfprice = new javax.swing.JTextField();
        cbauthor = new javax.swing.JComboBox();
        sptbltampil = new javax.swing.JScrollPane();
        tabeltampil = new javax.swing.JTable();
        idd = new javax.swing.JLabel();
        lblsearch = new javax.swing.JLabel();
        tfsearch = new javax.swing.JTextField();
        cbby = new javax.swing.JComboBox<String>();
        pnlpink = new javax.swing.JPanel();
        btnsave = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        lblfind = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        pnlbiru.setBackground(new java.awt.Color(153, 153, 255));

        pnlabu.setBackground(new java.awt.Color(204, 204, 204));

        lblform.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        lblform.setText("FORM DATA BOOK in GRAMEDIA SAMARINDA");

        javax.swing.GroupLayout pnlabuLayout = new javax.swing.GroupLayout(pnlabu);
        pnlabu.setLayout(pnlabuLayout);
        pnlabuLayout.setHorizontalGroup(
            pnlabuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlabuLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(lblform)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlabuLayout.setVerticalGroup(
            pnlabuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlabuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblform)
                .addContainerGap())
        );

        pnlhijau.setBackground(new java.awt.Color(0, 153, 0));

        lbltitle.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        lbltitle.setForeground(new java.awt.Color(255, 255, 255));
        lbltitle.setText("Title");

        lblauthor.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        lblauthor.setForeground(new java.awt.Color(255, 255, 255));
        lblauthor.setText("Author");

        lblprice.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        lblprice.setForeground(new java.awt.Color(255, 255, 255));
        lblprice.setText("Price");

        cbauthor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dean Koontz", "J.K Rowling", "Stephenie Meyer", "Tom Clancy" }));

        tabeltampil.setBackground(new java.awt.Color(204, 102, 255));
        tabeltampil.setFont(new java.awt.Font("Segoe Print", 0, 10)); // NOI18N
        tabeltampil.setForeground(new java.awt.Color(255, 255, 153));
        tabeltampil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tite", "Author", "Price"
            }
        ));
        tabeltampil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeltampilMouseClicked(evt);
            }
        });
        tabeltampil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabeltampilKeyReleased(evt);
            }
        });
        sptbltampil.setViewportView(tabeltampil);

        idd.setBackground(new java.awt.Color(255, 255, 255));
        idd.setForeground(new java.awt.Color(255, 255, 255));

        lblsearch.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        lblsearch.setForeground(new java.awt.Color(255, 255, 255));
        lblsearch.setText("Search");

        tfsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfsearchKeyReleased(evt);
            }
        });

        cbby.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Title", "Author", "Price" }));

        pnlpink.setBackground(new java.awt.Color(255, 153, 153));

        btnsave.setBackground(new java.awt.Color(0, 204, 204));
        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnupdate.setBackground(new java.awt.Color(0, 204, 204));
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(0, 204, 204));
        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnexit.setBackground(new java.awt.Color(0, 204, 204));
        btnexit.setText("Exit");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlpinkLayout = new javax.swing.GroupLayout(pnlpink);
        pnlpink.setLayout(pnlpinkLayout);
        pnlpinkLayout.setHorizontalGroup(
            pnlpinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlpinkLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        pnlpinkLayout.setVerticalGroup(
            pnlpinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlpinkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlpinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlpinkLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlpinkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnexit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblfind.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        lblfind.setForeground(new java.awt.Color(255, 255, 255));
        lblfind.setText("Find Book By : ");

        javax.swing.GroupLayout pnlhijauLayout = new javax.swing.GroupLayout(pnlhijau);
        pnlhijau.setLayout(pnlhijauLayout);
        pnlhijauLayout.setHorizontalGroup(
            pnlhijauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlhijauLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlhijauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlhijauLayout.createSequentialGroup()
                        .addComponent(sptbltampil, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(pnlhijauLayout.createSequentialGroup()
                        .addComponent(lblsearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblfind)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbby, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
            .addGroup(pnlhijauLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(idd, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlhijauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlhijauLayout.createSequentialGroup()
                        .addComponent(lblprice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tfprice, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlhijauLayout.createSequentialGroup()
                        .addComponent(lblauthor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbauthor, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlhijauLayout.createSequentialGroup()
                        .addComponent(lbltitle)
                        .addGap(27, 27, 27)
                        .addComponent(tftitle, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlpink, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlhijauLayout.setVerticalGroup(
            pnlhijauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlhijauLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlhijauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlhijauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbltitle)
                        .addComponent(tftitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(idd, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlhijauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblauthor)
                    .addComponent(cbauthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlhijauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblprice)
                    .addComponent(tfprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlpink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(pnlhijauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblsearch)
                    .addComponent(tfsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblfind))
                .addGap(32, 32, 32)
                .addComponent(sptbltampil, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlbiruLayout = new javax.swing.GroupLayout(pnlbiru);
        pnlbiru.setLayout(pnlbiruLayout);
        pnlbiruLayout.setHorizontalGroup(
            pnlbiruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbiruLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlbiruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlabu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlhijau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlbiruLayout.setVerticalGroup(
            pnlbiruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbiruLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlabu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlhijau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlbiru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlbiru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabeltampilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeltampilMouseClicked
        // TODO add your handling code here:
        //Event mouseclicked yang berfungsi jika tabeltampil di klik dengan mouse
        btnupdate.setEnabled(true);
        btndelete.setEnabled(true);
        int baris = tabeltampil.getSelectedRow();
        String title=tabeltampil.getValueAt(baris, 0).toString();
        String author=tabeltampil.getValueAt(baris, 1).toString();
        String price=tabeltampil.getValueAt(baris, 2).toString();
        String ids = tabeltampil.getValueAt(baris, 3).toString();
        
        idd.setText(ids);
        tftitle.setText(title);
        cbauthor.setSelectedItem(author);
        tfprice.setText(price);
        
        
       
        

    }//GEN-LAST:event_tabeltampilMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        InitTable();
        TampilData(); //Memanggil fungsi TampilData()
    }//GEN-LAST:event_formComponentShown

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        //Event actionperformed yang berfungsi untuk memberikan aksi jika tombol buttonsave diklik
        TambahData();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        TampilData();
        tftitle.setText("");
        tfprice.setText("");
//        idd.setText("");
        
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        //Event actionperformed yang berfungsi untuk memberikan aksi jika tombol buttonexit diklik
        System.exit(0);
    }//GEN-LAST:event_btnexitActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        //Event actionperformed yang berfungsi untuk memberikan aksi jika tombol buttonupdate diklik
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        UpdateData();
        TampilData();
        tftitle.setText("");
        tfprice.setText("");
//        idd.setText("");
    }//GEN-LAST:event_btnupdateActionPerformed

    private void tabeltampilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabeltampilKeyReleased
        // TODO add your handling code here:
        //Event keyreleased yang berfungsi saat tombol keyboard dilepas
        int baris = tabeltampil.getSelectedRow();
        String title=tabeltampil.getValueAt(baris, 0).toString();
        String author=tabeltampil.getValueAt(baris, 1).toString();
        String price=tabeltampil.getValueAt(baris, 2).toString();
        String ids = tabeltampil.getValueAt(baris, 3).toString();
        
        idd.setText(ids);
        tftitle.setText(title);
        cbauthor.setSelectedItem(author);
        tfprice.setText(price);
    }//GEN-LAST:event_tabeltampilKeyReleased

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
       //Event actionperformed yang berfungsi untuk memberikan aksi jika tombol buttondelete diklik
        HapusData();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        TampilData();
        tftitle.setText("");
        tfprice.setText("");
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
    }//GEN-LAST:event_btndeleteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        //Event windowopened yang berfungsi untuk memberikan even/aksi ketika aplikasi dibuka yaitu menset btnupdate n btndelete dlm keadaan tdk bs diklik 
        //tp bisa diklik ketika sudah melakukan tambahdata
        
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void tfsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfsearchKeyReleased
        // TODO add your handling code here:
        //Event keyreleased yang berfungsi saat tombol keyboard dilepas
        model.getDataVector().removeAllElements();//mendeklarasikan model yang mmengambil data vector dan meremove semua ellements
        model.fireTableDataChanged();//mendeklarasikan model yang di fireTableDataChanged
        CariData();
    }//GEN-LAST:event_tfsearchKeyReleased

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDataBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox cbauthor;
    private javax.swing.JComboBox<String> cbby;
    private javax.swing.JLabel idd;
    private javax.swing.JLabel lblauthor;
    private javax.swing.JLabel lblfind;
    private javax.swing.JLabel lblform;
    private javax.swing.JLabel lblprice;
    private javax.swing.JLabel lblsearch;
    private javax.swing.JLabel lbltitle;
    private javax.swing.JPanel pnlabu;
    private javax.swing.JPanel pnlbiru;
    private javax.swing.JPanel pnlhijau;
    private javax.swing.JPanel pnlpink;
    private javax.swing.JScrollPane sptbltampil;
    private javax.swing.JTable tabeltampil;
    private javax.swing.JTextField tfprice;
    private javax.swing.JTextField tfsearch;
    private javax.swing.JTextField tftitle;
    // End of variables declaration//GEN-END:variables
}
